package com.chun.netty.handler;

import com.chun.netty.packet.request.HeartBeatRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * @Author chun
 * @Date 2019/9/5 12:11
 */
public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 定时发送心跳的间隔 秒
     */
    private static final int HEARTBEAT_INTERVAL = 5;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        sendHeartBeat(ctx);
        super.channelActive(ctx);
    }

    /**
     * 发送心跳
     * @param ctx
     */
    private void sendHeartBeat(ChannelHandlerContext ctx) {
        ctx.executor().schedule(() -> {
            if(ctx.channel().isActive()){
                ctx.channel().writeAndFlush(new HeartBeatRequestPacket());
                sendHeartBeat(ctx);
            }
        }, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }
}
