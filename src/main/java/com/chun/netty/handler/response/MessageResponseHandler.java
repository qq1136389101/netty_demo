package com.chun.netty.handler.response;

import com.chun.netty.packet.request.MessageRequestPacket;
import com.chun.netty.packet.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author chun
 * @Date 2019/9/3 11:11
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 1000; i++) {
            MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
            messageRequestPacket.setMessage("测试拆包粘包--数组下标越界异常一般是超出数组长度去获取值引起。");
            ctx.channel().writeAndFlush(messageRequestPacket);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket) throws Exception {
        System.out.println("收到服务器的消息:" + messageResponsePacket.getMsg());
    }
}
