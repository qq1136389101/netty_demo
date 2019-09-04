package com.chun.netty.packet.var;

import com.chun.netty.packet.command.var.CommandVar;
import com.chun.netty.packet.request.CreateGroupRequestPacket;
import com.chun.netty.packet.request.LoginRequestPacket;
import com.chun.netty.packet.request.MessageRequestPacket;
import com.chun.netty.packet.response.CreateGroupResponsePacket;
import com.chun.netty.packet.response.LoginResponsePacket;
import com.chun.netty.packet.response.MessageResponsePacket;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author chun
 * @Date 2019/8/29 14:44
 */
public class PacketVar {

    /**
     * 魔术
     */
    public static final int MAGIC_NUMBER = 0x12345678;

    /**
     * 版本号
     */
    public static final byte VERSION = 1;

    /**
     * 请求命令与数据包映射常量
     */
    public final static Map PACKET_CLASS = new HashMap();

    /**
     * 响应命令与数据包映射常量
     */
    public final static Map RESPONSE_PACKET_CLASS = new HashMap();

    static {
        // 请求
        PACKET_CLASS.put(CommandVar.LOGIN_COMMAND, LoginRequestPacket.class);
        PACKET_CLASS.put(CommandVar.MESSAGE_COMMAND, MessageRequestPacket.class);
        PACKET_CLASS.put(CommandVar.CREATE_GROUP_COMMAND, CreateGroupRequestPacket.class);

        // 响应
        RESPONSE_PACKET_CLASS.put(CommandVar.LOGIN_COMMAND, LoginResponsePacket.class);
        RESPONSE_PACKET_CLASS.put(CommandVar.MESSAGE_COMMAND, MessageResponsePacket.class);
        RESPONSE_PACKET_CLASS.put(CommandVar.CREATE_GROUP_COMMAND, CreateGroupResponsePacket.class);
    }
}
