package com.chun.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @Author chun
 * @Date 2019/8/28 10:13
 */
public class NettyServer {

    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .handler(new ChannelInitializer<NioServerSocketChannel>() {
                    @Override
                    protected void initChannel(NioServerSocketChannel nioServerSocketChannel) throws Exception {
                        System.out.println(nioServerSocketChannel.attr(AttributeKey.valueOf("serverName")));
                        System.out.println("服务初始化");
                    }
                })
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new FirstServerHandler());
                    }
                });
        serverBootstrap.attr(AttributeKey.newInstance("serverName"), "nettyServer");
        serverBootstrap.childAttr(AttributeKey.newInstance("childAttrTest"), "childAttrTest");
        bind(serverBootstrap, 3306);
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
