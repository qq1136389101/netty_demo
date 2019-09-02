package com.chun.netty.packet.command;

import com.chun.netty.packet.Packet;
import com.chun.netty.packet.response.CommonResponse;
import io.netty.channel.ChannelHandlerContext;

/**
 * @Author chun
 * @Date 2019/8/28 16:31
 */
public interface Command {

    /**
     * 处理请求
     *
     * @param ctx
     * @param packet
     */
    public void run(ChannelHandlerContext ctx, Packet packet);

    /**
     * 处理响应
     *
     * @param ctx
     * @param commonResponse
     */
    public void runResponse(ChannelHandlerContext ctx, CommonResponse commonResponse);
}
