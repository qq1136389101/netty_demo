package com.chun.netty;

import com.chun.netty.handler.*;
import com.chun.netty.handler.request.*;
import com.chun.netty.packet.PacketSpliter;
import com.chun.netty.var.CommonVar;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @Author chun
 * @Date 2019/8/29 15:46
 */
public class Server {

    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup boos = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap.group(boos, worker)
            .channel(NioServerSocketChannel.class)
            .childHandler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                    // 心跳检测
                    nioSocketChannel.pipeline().addLast(new IMIdleStateHandler());

                    // 拆包粘包解决， 注释该行会可能会解析 byteBuf 失败, 导致数组下标异常
                    nioSocketChannel.pipeline().addLast(new PacketSpliter());

                    // 编码解码
                    nioSocketChannel.pipeline().addLast(PacketRequestCodeHandler.INSTANCE);

                    // 业务处理
                    nioSocketChannel.pipeline().addLast(HeartBeatRequestHandler.INSTANCE);
                    nioSocketChannel.pipeline().addLast(LoginRequestHandler.INSTANCE);
                    // AuthHandler 以下的 handler 都必须登录后才会执行
                    nioSocketChannel.pipeline().addLast(AuthHandler.INSTANCE);
                    nioSocketChannel.pipeline().addLast(IMRequestHandler.INSTANCE);
                }
            });

        bind(serverBootstrap, CommonVar.PORT);
    }

    /**
     * 递归绑定端口, 每次失败端口加一
     *
     * @param serverBootstrap
     * @param port
     */
    public static void bind(ServerBootstrap serverBootstrap, int port){
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if(future.isSuccess()){
                    System.out.println("绑定端口:" + port + "成功");
                }else{
                    System.out.println("绑定端口:" + port + "失败");
                    bind(serverBootstrap, port+1);
                }
            }
        });
    }
}
