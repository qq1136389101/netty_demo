package com.chun.netty.packet.var;

import com.chun.netty.packet.command.var.CommandVar;
import com.chun.netty.packet.request.LoginPacket;
import com.chun.netty.packet.response.LoginResponsePacket;

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
        PACKET_CLASS.put(CommandVar.LOGIN_COMMAND, LoginPacket.class);

        // 响应
        RESPONSE_PACKET_CLASS.put(CommandVar.LOGIN_COMMAND, LoginResponsePacket.class);
    }
}
