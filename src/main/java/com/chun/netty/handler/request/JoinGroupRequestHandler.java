package com.chun.netty.handler.request;

import com.chun.netty.packet.request.JoinGroupRequestPacket;
import com.chun.netty.packet.request.LoginRequestPacket;
import com.chun.netty.packet.response.JoinGroupResponsePacket;
import com.chun.netty.packet.response.LoginResponsePacket;
import com.chun.netty.util.Session;
import com.chun.netty.util.SessionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * 登录
 *
 * @Author chun
 * @Date 2019/9/3 11:11
 */
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, JoinGroupRequestPacket joinGroupRequestPacket) throws Exception {
        // 获取群名
        String groupName = joinGroupRequestPacket.getGroupName();

        // 处理请求
        ChannelGroup channelGroup = SessionUtils.getChannelGroupByGroupName(groupName);
        channelGroup.add(channelHandlerContext.channel());

        // 发送响应
        JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket(200, "加入群【"+ groupName +"】成功");
        channelHandlerContext.channel().writeAndFlush(joinGroupResponsePacket);
    }
}
