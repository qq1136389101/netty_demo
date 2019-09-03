package com.chun.netty.handler;

import com.chun.netty.packet.Packet;
import com.chun.netty.packet.PacketUtils;
import com.chun.netty.serializer.SerializerFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author chun
 * @Date 2019/9/3 11:20
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        /**
         * byteBuf 必须使用 write() 方法, 不能直接使用 = 赋值
         */
        // 错误使用
//        byteBuf = PacketUtils.encode(packet);

        // 正确使用
        PacketUtils.encode(byteBuf, packet);
    }
}
