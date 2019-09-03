package com.chun.netty.handler;

import com.chun.netty.packet.PacketUtils;
import com.chun.netty.packet.command.var.CommandTypeVar;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class RequestPacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) {
        out.add(PacketUtils.decode(in, CommandTypeVar.REQUEST));
    }
}