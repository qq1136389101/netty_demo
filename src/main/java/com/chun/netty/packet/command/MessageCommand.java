package com.chun.netty.packet.command;

import com.chun.netty.packet.Packet;
import com.chun.netty.packet.PacketUtils;
import com.chun.netty.packet.request.LoginRequestPacket;
import com.chun.netty.packet.request.MessageRequestPacket;
import com.chun.netty.packet.response.CommonResponse;
import com.chun.netty.packet.response.LoginResponsePacket;
import com.chun.netty.packet.response.MessageResponsePacket;
import com.chun.netty.serializer.SerializerFactory;
import com.chun.netty.util.LoginUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;

/**
 * 登录操作
 *
 * @Author chun
 * @Date 2019/8/28 16:59
 */
public class MessageCommand implements Command {

    @Override
    public void run(ChannelHandlerContext ctx, Packet packet) {
        if(packet instanceof MessageRequestPacket){
            MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
            System.out.println("收到客户端消息:" + messageRequestPacket.getMessage());

            MessageResponsePacket messageResponsePacket
                    = new MessageResponsePacket(200, "服务端回复【" + messageRequestPacket.getMessage() + "】");
            ByteBuf byteBuf = PacketUtils.encode(messageResponsePacket);
            ctx.channel().writeAndFlush(byteBuf);
        }
    }

    @Override
    public void runResponse(ChannelHandlerContext ctx, CommonResponse commonResponse) {
        if(commonResponse instanceof MessageResponsePacket){
            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) commonResponse;
            System.out.println("收到服务器的消息:" + messageResponsePacket.getMsg());
        }
    }
}
