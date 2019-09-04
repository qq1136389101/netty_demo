package com.chun.netty.handler.request;

import com.chun.netty.packet.request.LogoutRequestPacket;
import com.chun.netty.packet.response.LogoutResponsePacket;
import com.chun.netty.util.Session;
import com.chun.netty.util.SessionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 登录
 *
 * @Author chun
 * @Date 2019/9/3 11:11
 */
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogoutRequestPacket logoutRequestPacket) throws Exception {
        Session session = SessionUtils.getSession(channelHandlerContext.channel());
        System.out.println("用户【"+ session.getUserName() +"】退出登录");
        SessionUtils.logout(channelHandlerContext.channel());

        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        channelHandlerContext.channel().writeAndFlush(logoutResponsePacket);
    }
}
