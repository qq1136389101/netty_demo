package com.chun.netty.handler.response;

import com.alibaba.fastjson.JSONObject;
import com.chun.netty.packet.response.JoinGroupResponsePacket;
import com.chun.netty.packet.response.SendToGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author chun
 * @Date 2019/9/3 11:11
 */
public class SendToGroupResponseHandler extends SimpleChannelInboundHandler<SendToGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, SendToGroupResponsePacket sendToGroupResponsePacket) throws Exception {
        if(sendToGroupResponsePacket.getCode() == 200){
            JSONObject jsonObject = (JSONObject) JSONObject.parse(JSONObject.toJSONString(sendToGroupResponsePacket.getData()));
            String sendUser = jsonObject.getString("sendUser");
            String msg = jsonObject.getString("msg");
            String groupName = jsonObject.getString("groupName");

            System.out.println("收到群【" + groupName + "】中【"+ sendUser +"】发送的消息: "+ msg);
        }else{
            System.out.println(sendToGroupResponsePacket.getMsg());
        }
    }
}
