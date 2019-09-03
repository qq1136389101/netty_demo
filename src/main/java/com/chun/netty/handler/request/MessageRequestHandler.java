package com.chun.netty.handler.request;

import com.chun.netty.packet.PacketUtils;
import com.chun.netty.packet.request.LoginRequestPacket;
import com.chun.netty.packet.request.MessageRequestPacket;
import com.chun.netty.packet.response.LoginResponsePacket;
import com.chun.netty.packet.response.MessageResponsePacket;
import com.chun.netty.serializer.SerializerFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author chun
 * @Date 2019/9/3 11:11
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageRequestPacket messageRequestPacket) throws Exception {
        System.out.println("收到客户端消息:" + messageRequestPacket.getMessage());

        MessageResponsePacket messageResponsePacket
                = new MessageResponsePacket(200, "服务端回复【" + messageRequestPacket.getMessage() + "】");

        // 发送响应
        channelHandlerContext.channel().writeAndFlush(messageResponsePacket);
    }
}
