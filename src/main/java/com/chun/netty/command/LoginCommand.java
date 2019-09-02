package com.chun.netty.command;

import com.chun.netty.packet.request.LoginPacket;
import com.chun.netty.packet.Packet;
import com.chun.netty.packet.response.LoginResponsePacket;
import com.chun.netty.serializer.SerializerFactory;
import com.chun.netty.util.PacketUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * @Author chun
 * @Date 2019/8/28 16:59
 */
public class LoginCommand implements Command {

    @Override
    public void run(ChannelHandlerContext ctx, Packet packet) {
        if(packet instanceof LoginPacket){
            LoginPacket loginPacket = (LoginPacket) packet;
            LoginResponsePacket loginResponsePacket = null;
            if(validate(loginPacket)){
                // 校验成功
                loginResponsePacket = new LoginResponsePacket(200, "登录成功");
            }else{
                // 校验失败
                loginResponsePacket = new LoginResponsePacket(401, "账号密码错误");
            }

            ByteBuf byteBuf = PacketUtils.encode(loginResponsePacket, SerializerFactory.getSerializer());
            ctx.channel().writeAndFlush(byteBuf);
        }
    }

    private boolean validate(LoginPacket loginPacket){
        if(loginPacket.getUserName().equals("zhangsan") && loginPacket.getPassword().equals("123456")){
            return true;
        }else{
            return false;
        }
    }
}
