package com.chun.netty.handler.request;

import com.chun.netty.packet.request.HeartBeatRequestPacket;
import com.chun.netty.packet.response.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author chun
 * @Date 2019/9/3 11:11
 */
@ChannelHandler.Sharable
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {

    // 单例
    public static final HeartBeatRequestHandler INSTANCE = new HeartBeatRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HeartBeatRequestPacket heartBeatRequestPacket) throws Exception {
        channelHandlerContext.channel().writeAndFlush(new HeartBeatResponsePacket());
    }
}
