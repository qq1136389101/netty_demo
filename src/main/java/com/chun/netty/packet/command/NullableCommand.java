package com.chun.netty.packet.command;

import com.chun.netty.packet.Packet;
import com.chun.netty.packet.PacketUtils;
import com.chun.netty.packet.request.MessageRequestPacket;
import com.chun.netty.packet.response.CommonResponse;
import com.chun.netty.packet.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * 登录操作
 *
 * @Author chun
 * @Date 2019/8/28 16:59
 */
public class NullableCommand implements Command {

    @Override
    public void run(ChannelHandlerContext ctx, Packet packet) {
        System.out.println("命令不存在");
    }

    @Override
    public void runResponse(ChannelHandlerContext ctx, CommonResponse commonResponse) {
        System.out.println("命令不存在");
    }
}
