package com.chun.netty.handler.response;

import com.chun.netty.packet.response.JoinGroupResponsePacket;
import com.chun.netty.packet.response.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author chun
 * @Date 2019/9/3 11:11
 */
@ChannelHandler.Sharable
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {

    // 单例
    public static final QuitGroupResponseHandler INSTANCE = new QuitGroupResponseHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, QuitGroupResponsePacket quitGroupResponsePacket) throws Exception {
        System.out.println(quitGroupResponsePacket.getMsg());
    }
}
