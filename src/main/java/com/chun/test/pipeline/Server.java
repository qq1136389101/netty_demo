package com.chun.test.pipeline;

import com.chun.netty.var.CommonVar;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Author chun
 * @Date 2019/9/2 18:35
 */
public class Server {

    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap.group(boss, worker)
            .channel(NioServerSocketChannel.class)
            .childHandler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                    // inBound 处理数据逻辑
                    nioSocketChannel.pipeline().addLast(new InboundHandlerA());
                    nioSocketChannel.pipeline().addLast(new InboundHandlerB());
                    nioSocketChannel.pipeline().addLast(new InboundHandlerC());
                }
            })
            .bind(CommonVar.PORT);
        ;
    }
}
