package com.chun.netty.handler;

import com.chun.netty.packet.command.Command;
import com.chun.netty.packet.command.CommandFactory;
import com.chun.netty.packet.Packet;
import com.chun.netty.packet.command.var.CommandTypeVar;
import com.chun.netty.util.PacketUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author chun
 * @Date 2019/8/29 15:32
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("-------------- 服务端启动成功 ------------");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        // 解码
        Packet packet = PacketUtils.decode(byteBuf, CommandTypeVar.REQUEST);
        Command command = CommandFactory.getCommand(packet.getCommand());
        command.run(ctx, packet);
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
