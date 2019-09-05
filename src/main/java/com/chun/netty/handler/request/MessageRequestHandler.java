package com.chun.netty.handler.request;

import com.chun.netty.packet.request.MessageRequestPacket;
import com.chun.netty.packet.response.MessageResponsePacket;
import com.chun.netty.util.Session;
import com.chun.netty.util.SessionUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 接收消息
 *
 * @Author chun
 * @Date 2019/9/3 11:11
 */
@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    // 单例
    public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageRequestPacket messageRequestPacket) throws Exception {
        String[] message = messageRequestPacket.getMessage().split(" ");
        String userName = message[0];
        String messageDetail = message[1];

        Channel channel = SessionUtils.getChannelByUserName(userName);
        if(channel == null){
            // 接收人不在线, 给发送人发送信息
            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setCode(402);
            messageResponsePacket.setMsg("当前用户不在线");
            channelHandlerContext.channel().writeAndFlush(messageResponsePacket);
        }else{
            Session session = SessionUtils.getSession(channelHandlerContext.channel());
            // 给接收人发送信息
            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setMsg(session.getUserName() + " -> " + messageDetail);
            channel.writeAndFlush(messageResponsePacket);
        }
    }

}
