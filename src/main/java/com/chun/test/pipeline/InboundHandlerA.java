package com.chun.test.pipeline;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author chun
 * @Date 2019/9/2 18:40
 */
public class InboundHandlerA extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("InboundA:" + msg);

        // 通过调用super调用下一个处理器
        super.channelRead(ctx, msg);
    }
}
