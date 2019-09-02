package com.chun.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author chun
 * @Date 2019/8/28 10:16
 */
public class NettyClient {

    final static int maxRetry = 5;

    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) {
                        ch.pipeline().addLast(new FirstClientHandler());
                    }
                });

        ChannelFuture channelFuture = connect(bootstrap, "127.0.0.1", 3307, maxRetry);
        Channel channel = channelFuture.channel();
        while (true) {
            channel.writeAndFlush(new Date() + ": hello world!");
            Thread.sleep(2000);
        }
    }


    public static ChannelFuture connect(Bootstrap bootstrap, String host, int port, int retry){
        System.out.println("开始重连....");
        return bootstrap.connect(host, port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if(future.isSuccess()){
                    System.out.println("连接成功");
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
}
