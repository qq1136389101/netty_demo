package com.chun.netty.handler.request;

import com.chun.netty.packet.request.ListGroupRequestPacket;
import com.chun.netty.packet.request.LoginRequestPacket;
import com.chun.netty.packet.response.ListGroupResponsePacket;
import com.chun.netty.packet.response.LoginResponsePacket;
import com.chun.netty.util.Session;
import com.chun.netty.util.SessionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author chun
 * @Date 2019/9/3 11:11
 */
public class ListGroupRequestHandler extends SimpleChannelInboundHandler<ListGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ListGroupRequestPacket listGroupRequestPacket) throws Exception {
        String groupName = listGroupRequestPacket.getGroupName();

        // 获取群里面的所有用户信息
        ChannelGroup channels = SessionUtils.getChannelGroupByGroupName(groupName);
        List<Session> sessions = new ArrayList<>();
        if(channels != null){
            channels.forEach(v -> {
                Session session = SessionUtils.getSession(v);
                if(session != null){
                    sessions.add(session);
                }
            });
        }

        // 响应给客户端
        Map data = new HashMap();
        data.put("groupName", groupName);
        data.put("sessions", sessions);
        ListGroupResponsePacket listGroupResponsePacket = new ListGroupResponsePacket(data);
        channelHandlerContext.channel().writeAndFlush(listGroupResponsePacket);
    }
}
