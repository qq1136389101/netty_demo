package com.chun.netty.handler;

import com.chun.netty.packet.response.MessageResponsePacket;
import com.chun.netty.util.LoginUtils;
import com.chun.netty.var.AttributeVar;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author chun
 * @Date 2019/9/3 17:34
 */
public class AuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(LoginUtils.hasLogin(ctx.channel())){
            // 校验身份完成之后就不用再校验了
            ctx.pipeline().remove(this);
            ctx.fireChannelRead(msg);
        }else {
            ctx.channel().close();
        }
    }

    /**
     * ctx.pipeline().remove(this); 和 ctx.channel().close(); 都会触发该方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        if(LoginUtils.hasLogin(ctx.channel())){
            System.out.println("当前账号已登录，不需要再次校验");
            messageResponsePacket.setCode(200);
            messageResponsePacket.setMsg("当前账号已登录，不需要再次校验");
            ctx.channel().writeAndFlush(messageResponsePacket);
        }else{
            System.out.println("账号登录失败，强制关闭连接");
            messageResponsePacket.setCode(401);
            messageResponsePacket.setMsg("账号登录失败，强制关闭连接");
            ctx.channel().writeAndFlush(messageResponsePacket);
        }
        super.handlerRemoved(ctx);
    }
}
