package com.chun.netty.handler.request;

import com.chun.netty.packet.PacketUtils;
import com.chun.netty.packet.request.LoginRequestPacket;
import com.chun.netty.packet.response.LoginResponsePacket;
import com.chun.netty.serializer.SerializerFactory;
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
            // 校验成功
            loginResponsePacket = new LoginResponsePacket(200, "登录成功");
        }else{
            // 校验失败
            loginResponsePacket = new LoginResponsePacket(401, "账号密码错误");
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
        if(loginPacket.getUserName().equals("zhangsan") && loginPacket.getPassword().equals("123456")){
            return true;
        }else{
            return false;
        }
    }
}
