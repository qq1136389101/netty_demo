package com.chun.netty.packet.command;

import com.chun.netty.packet.request.LoginPacket;
import com.chun.netty.packet.Packet;
import com.chun.netty.packet.response.CommonResponse;
import com.chun.netty.packet.response.LoginResponsePacket;
import com.chun.netty.serializer.SerializerFactory;
import com.chun.netty.util.PacketUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * 登录操作
 *
 * @Author chun
 * @Date 2019/8/28 16:59
 */
public class LoginCommand implements Command {

    @Override
    public void run(ChannelHandlerContext ctx, Packet packet) {
        if(packet instanceof LoginPacket){
            // 封装响应
            LoginPacket loginPacket = (LoginPacket) packet;
            LoginResponsePacket loginResponsePacket = null;
            if(validate(loginPacket)){
                // 校验成功
                loginResponsePacket = new LoginResponsePacket(200, "登录成功");
            }else{
                // 校验失败
                loginResponsePacket = new LoginResponsePacket(401, "账号密码错误");
            }

            // 发送响应
            ByteBuf byteBuf = PacketUtils.encode(loginResponsePacket, SerializerFactory.getSerializer());
            ctx.channel().writeAndFlush(byteBuf);
        }
    }

    @Override
    public void runResponse(ChannelHandlerContext ctx, CommonResponse commonResponse) {
        if(commonResponse instanceof LoginResponsePacket){
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) commonResponse;
            if(loginResponsePacket.getCode() == 200){
                System.out.println("登录成功");
            }else{
                System.out.println("登录失败: " + loginResponsePacket.getMsg());
            }
        }
    }

    /**
     * 登入请求校验
     *
     * @param loginPacket
     * @return
     */
    private boolean validate(LoginPacket loginPacket){
        if(loginPacket.getUserName().equals("zhangsan") && loginPacket.getPassword().equals("123456")){
            return true;
        }else{
            return false;
        }
    }
}
