package com.chun.test.pipeline;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author chun
 * @Date 2019/9/2 18:40
 */
public class InboundHandlerC extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("InboundC:" + msg);
        super.channelRead(ctx, msg);
    }
}
