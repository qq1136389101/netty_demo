package com.chun.netty.handler.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chun.netty.packet.response.LoginResponsePacket;
import com.chun.netty.packet.response.LogoutResponsePacket;
import com.chun.netty.util.Session;
import com.chun.netty.util.SessionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author chun
 * @Date 2019/9/3 11:11
 */
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogoutResponsePacket logoutResponsePacket) throws Exception {
        if(logoutResponsePacket.getCode() == 200){
            SessionUtils.logout(channelHandlerContext.channel());
        }
        System.out.println("退出登录成功");
    }
}
