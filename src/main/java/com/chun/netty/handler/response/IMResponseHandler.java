package com.chun.netty.handler.response;

import com.chun.netty.packet.Packet;
import com.chun.netty.packet.command.var.CommandVar;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author chun
 * @Date 2019/9/3 11:11
 */
@ChannelHandler.Sharable
public class IMResponseHandler extends SimpleChannelInboundHandler<Packet> {

    // 单例
    public static final IMResponseHandler INSTANCE = new IMResponseHandler();

    private static final Map<Byte, SimpleChannelInboundHandler> handlerMap = new HashMap<>();

    static {
        handlerMap.put(CommandVar.LOGIN_COMMAND, LoginResponseHandler.INSTANCE);
        handlerMap.put(CommandVar.MESSAGE_COMMAND, MessageResponseHandler.INSTANCE);
        handlerMap.put(CommandVar.CREATE_GROUP_COMMAND, CreateGroupResponseHandler.INSTANCE);
        handlerMap.put(CommandVar.LOGOUT_COMMAND, LogoutResponseHandler.INSTANCE);
        handlerMap.put(CommandVar.LIST_GROUP, ListGroupResponseHandler.INSTANCE);
        handlerMap.put(CommandVar.JOIN_GROUP, JoinGroupResponseHandler.INSTANCE);
        handlerMap.put(CommandVar.QUIT_GROUP, QuitGroupResponseHandler.INSTANCE);
        handlerMap.put(CommandVar.SEND_TO_GROUP, SendToGroupResponseHandler.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {
        handlerMap.get(packet.getCommand()).channelRead(channelHandlerContext, packet);
    }
}
