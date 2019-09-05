package com.chun.netty.packet.command.var;

/**
 * @Author chun
 * @Date 2019/8/28 18:13
 */
public class CommandVar {

    /**
     * 登入
     */
    public static final byte LOGIN_COMMAND = 1;

    /**
     * 发送消息
     */
    public static final byte MESSAGE_COMMAND = 2;

    /**
     * 创建群
     */
    public static final byte CREATE_GROUP_COMMAND = 3;

    /**
     * 退出登录
     */
    public static final byte LOGOUT_COMMAND = 4;

    /**
     * 列出群成员
     */
    public static final byte LIST_GROUP = 5;

    /**
     * 加入群聊
     */
    public static final byte JOIN_GROUP = 6;
}
