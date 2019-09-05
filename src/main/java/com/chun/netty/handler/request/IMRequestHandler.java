package com.chun.netty.handler.request;

import com.chun.netty.packet.Packet;
import com.chun.netty.packet.command.var.CommandVar;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author chun
 * @Date 2019/9/5 11:11
 */
@ChannelHandler.Sharable
public class IMRequestHandler extends SimpleChannelInboundHandler<Packet>{

    // 单例
    public static final IMRequestHandler INSTANCE = new IMRequestHandler();

    private static final Map<Byte, SimpleChannelInboundHandler> handlerMap = new HashMap<>();

    static {
        handlerMap.put(CommandVar.MESSAGE_COMMAND, MessageRequestHandler.INSTANCE);
        handlerMap.put(CommandVar.CREATE_GROUP_COMMAND, CreateGroupRequestHandler.INSTANCE);
        handlerMap.put(CommandVar.LOGOUT_COMMAND, LogoutRequestHandler.INSTANCE);
        handlerMap.put(CommandVar.LIST_GROUP, ListGroupRequestHandler.INSTANCE);
        handlerMap.put(CommandVar.JOIN_GROUP, JoinGroupRequestHandler.INSTANCE);
        handlerMap.put(CommandVar.QUIT_GROUP, QuitGroupRequestHandler.INSTANCE);
        handlerMap.put(CommandVar.SEND_TO_GROUP, SendToGroupRequestHandler.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {
        handlerMap.get(packet.getCommand()).channelRead(channelHandlerContext, packet);
    }
}
