package com.chun.netty.handler.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chun.netty.handler.request.MessageRequestHandler;
import com.chun.netty.packet.request.LoginRequestPacket;
import com.chun.netty.packet.request.MessageRequestPacket;
import com.chun.netty.packet.response.LoginResponsePacket;
import com.chun.netty.util.LoginUtils;
import com.chun.netty.util.Session;
import com.chun.netty.util.SessionUtils;
import com.chun.netty.var.AttributeVar;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @Author chun
 * @Date 2019/9/3 11:11
 */
@ChannelHandler.Sharable
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    // 单例
    public static final LoginResponseHandler INSTANCE = new LoginResponseHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) throws Exception {
        System.out.println("接收到服务端的登录响应");
        if(loginResponsePacket.getCode() == 200){
            SessionUtils.login(JSONObject.toJavaObject((JSON) loginResponsePacket.getData(), Session.class), channelHandlerContext.channel());
            System.out.println("登录成功");
        }else{
            System.out.println("登录失败: " + loginResponsePacket.getMsg());
        }
    }
}
