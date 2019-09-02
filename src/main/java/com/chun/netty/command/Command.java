package com.chun.netty.command;

import com.chun.netty.packet.Packet;
import io.netty.channel.ChannelHandlerContext;

/**
 * @Author chun
 * @Date 2019/8/28 16:31
 */
public interface Command {

    public void run(ChannelHandlerContext ctx, Packet packet);
}
