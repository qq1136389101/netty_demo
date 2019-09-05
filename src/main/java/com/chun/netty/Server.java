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
                    // 原始方式
//                    nioSocketChannel.pipeline().addLast(new ServerHandler());

                    // 责任链方式
                    // 拆包粘包解决， 注释该行会可能会解析 byteBuf 失败, 导致数组下标异常
                    nioSocketChannel.pipeline().addLast(new PacketSpliter());

                    nioSocketChannel.pipeline().addLast(new RequestPacketDecoder());
                    nioSocketChannel.pipeline().addLast(new PacketEncoder());
                    nioSocketChannel.pipeline().addLast(new LoginRequestHandler());

                    // AuthHandler 以下的 handler 都必须登录后才会执行
                    nioSocketChannel.pipeline().addLast(new AuthHandler());

                    nioSocketChannel.pipeline().addLast(new MessageRequestHandler());
                    nioSocketChannel.pipeline().addLast(new CreateGroupRequestHandler());
                    nioSocketChannel.pipeline().addLast(new LogoutRequestHandler());
                    nioSocketChannel.pipeline().addLast(new ListGroupRequestHandler());
                    nioSocketChannel.pipeline().addLast(new JoinGroupRequestHandler());
                    nioSocketChannel.pipeline().addLast(new QuitGroupRequestHandler());
                    nioSocketChannel.pipeline().addLast(new SendToGroupRequestHandler());
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
