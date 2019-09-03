package com.chun.netty.handler.response;

import com.chun.netty.packet.request.LoginRequestPacket;
import com.chun.netty.packet.response.LoginResponsePacket;
import com.chun.netty.util.LoginUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @Author chun
 * @Date 2019/9/3 11:11
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("--------------- 客户端启动成功 ---------------");

        // 封装登录对象
        LoginRequestPacket loginPacket = new LoginRequestPacket();
        loginPacket.setId(UUID.randomUUID().toString());
        loginPacket.setUserName("zhangsan");
        loginPacket.setPassword("123456");

        // 发送登入请求
        System.out.println("向服务端发送登录请求");
        ctx.channel().writeAndFlush(loginPacket);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) throws Exception {
        System.out.println("接收到服务端的登录响应");
        if(loginResponsePacket.getCode() == 200){
            LoginUtils.login(channelHandlerContext.channel());
            System.out.println("登录成功");
        }else{
            LoginUtils.logout(channelHandlerContext.channel());
            System.out.println("登录失败: " + loginResponsePacket.getMsg());
        }
    }
}
