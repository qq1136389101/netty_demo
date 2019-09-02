package com.chun.netty.handler;

import com.chun.netty.packet.request.LoginPacket;
import com.chun.netty.serializer.SerializerFactory;
import com.chun.netty.util.PacketUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.UUID;

/**
 * @Author chun
 * @Date 2019/8/29 15:31
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("--------------- 客户端启动成功 ---------------");

        // 封装登录对象
        LoginPacket loginPacket = new LoginPacket();
        loginPacket.setId(UUID.randomUUID().toString());
        loginPacket.setUserName("zhangsan");
        loginPacket.setPassword("1234567");

        ByteBuf byteBuf = PacketUtils.encode(loginPacket, SerializerFactory.getSerializer());
        ctx.channel().writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("接收到服务端的数据:" + byteBuf.toString(CharsetUtil.UTF_8));
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
