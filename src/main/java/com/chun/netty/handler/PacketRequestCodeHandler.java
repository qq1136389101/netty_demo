package com.chun.netty.handler;

import com.chun.netty.packet.Packet;
import com.chun.netty.packet.PacketUtils;
import com.chun.netty.packet.command.var.CommandTypeVar;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * @Author chun
 * @Date 2019/9/5 11:42
 */
@ChannelHandler.Sharable
public class PacketRequestCodeHandler extends MessageToMessageCodec<ByteBuf, Packet> {

    // 单例
    public static final PacketRequestCodeHandler INSTANCE = new PacketRequestCodeHandler();

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, List<Object> list) throws Exception {
        list.add(PacketUtils.encode(packet));
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        list.add(PacketUtils.decode(byteBuf, CommandTypeVar.REQUEST));
    }
}
