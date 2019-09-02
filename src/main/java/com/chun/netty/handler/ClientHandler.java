package com.chun.netty.handler;

import com.chun.netty.packet.command.CommandFactory;
import com.chun.netty.packet.command.var.CommandTypeVar;
import com.chun.netty.packet.request.LoginPacket;
import com.chun.netty.packet.response.CommonResponse;
import com.chun.netty.serializer.SerializerFactory;
import com.chun.netty.util.PacketUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

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
        loginPacket.setPassword("123456");

        // 发送登入请求
        ByteBuf byteBuf = PacketUtils.encode(loginPacket, SerializerFactory.getSerializer());
        ctx.channel().writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        // 解析响应对象
        CommonResponse commonResponse = (CommonResponse) PacketUtils.decode(byteBuf, CommandTypeVar.RESPONSE);

        // 处理响应
        CommandFactory.getCommand(commonResponse.getCommand()).runResponse(ctx, commonResponse);
    }

    /**
     * 发送字符串消息给服务端
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
