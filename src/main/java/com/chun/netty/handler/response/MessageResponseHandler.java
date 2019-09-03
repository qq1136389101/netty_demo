package com.chun.netty.handler.response;

import com.chun.netty.packet.request.MessageRequestPacket;
import com.chun.netty.packet.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author chun
 * @Date 2019/9/3 11:11
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket) throws Exception {
        System.out.println("收到服务器的消息:" + messageResponsePacket.getMsg());
    }
}
