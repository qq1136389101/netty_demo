package com.chun.netty.handler;

import com.chun.netty.packet.response.MessageResponsePacket;
import com.chun.netty.util.LoginUtils;
import com.chun.netty.util.SessionUtils;
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
        if(SessionUtils.hasLogin(ctx.channel())){
            // 校验身份完成之后就不用再校验了
            ctx.pipeline().remove(this);
            ctx.fireChannelRead(msg);
        }else {
            ctx.channel().close();
        }
    }
}
