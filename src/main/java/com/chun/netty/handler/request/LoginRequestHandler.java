package com.chun.netty.handler.request;

import com.chun.netty.packet.PacketUtils;
import com.chun.netty.packet.request.LoginRequestPacket;
import com.chun.netty.packet.response.LoginResponsePacket;
import com.chun.netty.serializer.SerializerFactory;
import com.chun.netty.util.LoginUtils;
import com.chun.netty.util.Session;
import com.chun.netty.util.SessionUtils;
import com.chun.netty.var.AttributeVar;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @Author chun
 * @Date 2019/9/3 11:11
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {
        // 封装响应
        LoginResponsePacket loginResponsePacket = null;
        if(validate(loginRequestPacket)){
            // 保存登录信息
            Session session = new Session();
            session.setId(loginRequestPacket.getId());
            session.setUserName(loginRequestPacket.getUserName());
            SessionUtils.login(session, channelHandlerContext.channel());

            // 校验成功
            loginResponsePacket = new LoginResponsePacket(200, "登录成功", session);
        }else{
            // 校验失败
            loginResponsePacket = new LoginResponsePacket(401, "账号密码错误");
            SessionUtils.logout(channelHandlerContext.channel());
        }

        // 发送响应
        System.out.println("向客户端发送登录响应");
        channelHandlerContext.channel().writeAndFlush(loginResponsePacket);
    }

    /**
     * 登入请求校验
     *
     * @param loginPacket
     * @return
     */
    private boolean validate(LoginRequestPacket loginPacket){
        return true;
    }
}
