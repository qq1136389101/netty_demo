package com.chun.netty.handler.response;

import com.chun.netty.packet.response.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author chun
 * @Date 2019/9/3 11:11
 */
@ChannelHandler.Sharable
public class HeartBeatResponseHandler extends SimpleChannelInboundHandler<HeartBeatResponsePacket> {

    // 单例
    public static final HeartBeatResponseHandler INSTANCE = new HeartBeatResponseHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HeartBeatResponsePacket heartBeatResponseRequestPacket) throws Exception {
    }
}
