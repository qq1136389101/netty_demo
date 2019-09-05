package com.chun.netty;

import com.chun.netty.console.ConsoleCommand;
import com.chun.netty.console.ConsoleCommandFactory;
import com.chun.netty.handler.PacketEncoder;
import com.chun.netty.handler.ResponsePacketDecoder;
import com.chun.netty.handler.response.*;
import com.chun.netty.packet.PacketSpliter;
import com.chun.netty.packet.request.LoginRequestPacket;
import com.chun.netty.util.SessionUtils;
import com.chun.netty.var.AttributeVar;
import com.chun.netty.var.CommonVar;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author chun
 * @Date 2019/8/29 15:39
 */
public class Client {

    /**
     * 最大重连次数
     */
    final static int maxRetry = 5;

    public static void main(String[] args) {

        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        bootstrap.group(nioEventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel socketChannel) throws Exception {
                        // 拆包粘包解决， 注释该行会可能会解析 byteBuf 失败, 导致数组下标异常
                        socketChannel.pipeline().addLast(new PacketSpliter());

                        // 编码解码
                        socketChannel.pipeline().addLast(new ResponsePacketDecoder());
                        socketChannel.pipeline().addLast(new PacketEncoder());

                        socketChannel.pipeline().addLast(LoginResponseHandler.INSTANCE);
                        socketChannel.pipeline().addLast(IMResponseHandler.INSTANCE);
                    }
                });

        ChannelFuture channelFuture = connect(bootstrap, CommonVar.HOST, CommonVar.PORT, maxRetry);
        Channel channel = channelFuture.channel();
    }

    /**
     * 连接服务端
     *
     * @param bootstrap
     * @param host
     * @param port
     * @param retry 重试次数
     * @return
     */
    public static ChannelFuture connect(Bootstrap bootstrap, String host, int port, int retry){
        System.out.println("开始重连....");
        return bootstrap.connect(host, port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if(future.isSuccess()){
                    System.out.println("连接成功");

                    // 连接成功后启动控制台线程
                    Channel channel = ((ChannelFuture) future).channel();
                    startConsoleThread(channel);
                }else if(retry == 0){
                    System.out.println("连接次数已用完");
                }else{
                    int order = maxRetry - retry + 1;
                    int time = 1 << order;
                    System.out.println("连接失败, "+ time +"秒后开始第"+ order +"次重连");
                    bootstrap.config().group().schedule(() -> {
                        connect(bootstrap, host, port, retry-1);
                    }, time, TimeUnit.SECONDS);
                }
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        new Thread(() -> {
            while (!Thread.interrupted()){
                if(!SessionUtils.hasLogin(channel)){
                    // 登录
                    System.out.println("请输入用户名登录: ");
                    Scanner scanner = new Scanner(System.in);
                    String line = scanner.nextLine();

                    LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
                    loginRequestPacket.setId(UUID.randomUUID().toString());
                    loginRequestPacket.setUserName(line);
                    loginRequestPacket.setPassword("123456");

                    channel.writeAndFlush(loginRequestPacket);
                    channel.attr(AttributeVar.LOGIN).set(true);

                    // 发送登录请求后，停顿一段时间，等待登录响应再执行 while
                    waitForLoginResponse();
                }else{
                    System.out.println("请输入操作命令: " +
                            "1【创建群聊】, " +
                            "2【发送消息】, " +
                            "3【退出登录】, " +
                            "4【显示群成员】，" +
                            "5【加入群聊】，" +
                            "6【退出群聊】，" +
                            "7【发送群消息】");
                    Scanner scanner = new Scanner(System.in);
                    String line = scanner.nextLine();

                    ConsoleCommand command = ConsoleCommandFactory.getCommand(line);
                    command.exec(scanner, channel);

                    waitForLoginResponse();
                }
            }
        }).start();
    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ignored) {
        }
    }
}
