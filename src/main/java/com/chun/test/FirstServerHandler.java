package com.chun.test;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * @Author chun
 * @Date 2019/8/28 14:36
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 接收客户端的数据
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("服务端收到数据: " + buf.toString(Charset.forName("utf-8")));

        // 写数据给客户端
//        sendMsg(ctx, "你好，我是服务端");
    }

    /**
     * 发送消息给客户端
     *
     * @param ctx
     * @param msg       消息内容
     */
    private void sendMsg(ChannelHandlerContext ctx, String msg) {
        ByteBuf buf = ctx.alloc().buffer();
        byte[] outMsg = msg.getBytes();
        buf.writeBytes(outMsg);
        ctx.channel().writeAndFlush(buf);
    }
}
