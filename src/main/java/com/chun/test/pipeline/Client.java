package com.chun.test.pipeline;

import com.chun.netty.handler.ClientHandler;
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
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new ClientInboudHander());
                    }
                });


        ChannelFuture channelFuture = bootstrap.connect(CommonVar.HOST, CommonVar.PORT);
        Channel channel = channelFuture.channel();
    }

    public static ChannelFuture connect(Bootstrap bootstrap, String host, int port, int retry){
        return bootstrap.connect(host, port);
    }
}
