package com.chun.test.pipeline;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author chun
 * @Date 2019/9/2 18:51
 */
public class ClientInboudHander extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf byteBuf = ctx.alloc().buffer();
        byteBuf.writeBytes("客户端连接成功".getBytes());
        ctx.channel().writeAndFlush(byteBuf);
    }
}
