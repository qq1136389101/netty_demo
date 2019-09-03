package com.chun.netty;

import com.chun.netty.handler.PacketEncoder;
import com.chun.netty.handler.ResponsePacketDecoder;
import com.chun.netty.handler.response.LoginResponseHandler;
import com.chun.netty.handler.response.MessageResponseHandler;
import com.chun.netty.packet.PacketSpliter;
import com.chun.netty.packet.PacketUtils;
import com.chun.netty.packet.request.MessageRequestPacket;
import com.chun.netty.serializer.SerializerFactory;
import com.chun.netty.util.LoginUtils;
import com.chun.netty.var.CommonVar;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Scanner;
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
                        // 原始方式
//                        nioSocketChannel.pipeline().addLast(new ClientHandler());

                        // 责任链模式
                        // 拆包粘包解决， 注释该行会可能会解析 byteBuf 失败, 导致数组下标异常
                        socketChannel.pipeline().addLast(new PacketSpliter());

                        socketChannel.pipeline().addLast(new ResponsePacketDecoder());
                        socketChannel.pipeline().addLast(new PacketEncoder());
                        socketChannel.pipeline().addLast(new LoginResponseHandler());
                        socketChannel.pipeline().addLast(new MessageResponseHandler());
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
                // 判断是否登录
//                if(LoginUtils.hasLogin(channel)){
                    System.out.println("请输入消息发送至服务器: ");
                    Scanner scanner = new Scanner(System.in);
                    String line = scanner.nextLine();

                    MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
                    messageRequestPacket.setMessage(line);

                    channel.writeAndFlush(messageRequestPacket);
//                }
            }
        }).start();
    }
}
