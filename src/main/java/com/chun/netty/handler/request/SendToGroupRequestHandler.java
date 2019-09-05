package com.chun.netty.handler.request;

import com.chun.netty.packet.request.JoinGroupRequestPacket;
import com.chun.netty.packet.request.SendToGroupRequestPacket;
import com.chun.netty.packet.response.JoinGroupResponsePacket;
import com.chun.netty.packet.response.SendToGroupResponsePacket;
import com.chun.netty.util.Session;
import com.chun.netty.util.SessionUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import org.apache.commons.collections.MapUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录
 *
 * @Author chun
 * @Date 2019/9/3 11:11
 */
@ChannelHandler.Sharable
public class SendToGroupRequestHandler extends SimpleChannelInboundHandler<SendToGroupRequestPacket> {

    // 单例
    public static final SendToGroupRequestHandler INSTANCE = new SendToGroupRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, SendToGroupRequestPacket sendToGroupRequestPacket) throws Exception {

        ChannelGroup channels = SessionUtils.getChannelGroupByGroupName(sendToGroupRequestPacket.getGroupName());
        if(channels != null){
            Session session = SessionUtils.getSession(channelHandlerContext.channel());
            Map data = new HashMap();
            data.put("groupName", sendToGroupRequestPacket.getGroupName());
            data.put("sendUser", session.getUserName());
            data.put("msg", sendToGroupRequestPacket.getMsg());

            SendToGroupResponsePacket sendToGroupResponsePacket = new SendToGroupResponsePacket(data);
            channels.writeAndFlush(sendToGroupResponsePacket);
        }else{
            SendToGroupResponsePacket sendToGroupResponsePacket = new SendToGroupResponsePacket(402, "该群不存在");
            channelHandlerContext.channel().writeAndFlush(sendToGroupResponsePacket);
        }
    }
}
