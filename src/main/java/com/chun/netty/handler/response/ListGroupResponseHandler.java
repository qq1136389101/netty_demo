package com.chun.netty.handler.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chun.netty.packet.response.ListGroupResponsePacket;
import com.chun.netty.packet.response.LogoutResponsePacket;
import com.chun.netty.util.Session;
import com.chun.netty.util.SessionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

/**
 * @Author chun
 * @Date 2019/9/3 11:11
 */
public class ListGroupResponseHandler extends SimpleChannelInboundHandler<ListGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ListGroupResponsePacket listGroupResponsePacket) throws Exception {
        System.out.println(123);
        if(listGroupResponsePacket.getCode() == 200){
            // 数据解析
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(listGroupResponsePacket.getData());
            String groupName = jsonObject.getString("groupName");
            JSONArray sessions = jsonObject.getJSONArray("sessions");

            StringBuilder stringBuilder = new StringBuilder("群【" + groupName + "】中的人包括: ");
            sessions.forEach(session -> {
                Session session1 = JSONObject.toJavaObject((JSON) session, Session.class);
                stringBuilder.append(session1.getUserName() + ",");
            });
            System.out.println(stringBuilder.substring(0, stringBuilder.length()-1));
        }
    }
}
