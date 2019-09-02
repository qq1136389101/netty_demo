package com.chun.netty;

import com.chun.netty.handler.ClientHandler;
import com.chun.netty.var.CommonVar;
import com.chun.test.FirstClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

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
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new ClientHandler());
                    }
                });

        ChannelFuture channelFuture = connect(bootstrap, CommonVar.HOST, CommonVar.PORT, maxRetry);
        Channel channel = channelFuture.channel();
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
