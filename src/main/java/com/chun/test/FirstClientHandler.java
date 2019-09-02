package com.chun.test;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * @Author chun
 * @Date 2019/8/28 14:36
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {
    /**
     * 成功连接服务端执行
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端开始写数据");
        String msg = "你好， 我是客户端";
        sendMsg(ctx, msg);
    }

    /**
     * 接收到服务端的消息执行
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("从服务端接收到的消息: " + byteBuf.toString(Charset.forName("utf-8")));
    }

    /**
     * 发送消息给服务端
     *
     * @param ctx
     * @param msg   消息内容
     */
    private void sendMsg(ChannelHandlerContext ctx, String msg) {
        // 获取数据
        ByteBuf buf = ctx.alloc().buffer();
        byte[] bytes = msg.getBytes();
        buf.writeBytes(bytes);
        // 写数据
        ctx.channel().writeAndFlush(buf);
    }
}
