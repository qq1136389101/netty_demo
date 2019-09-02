package com.chun.netty;

import com.chun.netty.handler.ClientHandler;
import com.chun.netty.handler.ServerHandler;
import com.chun.netty.var.CommonVar;
import com.chun.test.FirstServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
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
                    nioSocketChannel.pipeline().addLast(new ServerHandler());
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
