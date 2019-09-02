package com.chun.netty.packet.request;

import com.chun.netty.packet.command.var.CommandVar;
import com.chun.netty.packet.Packet;
import lombok.Data;

/**
 * @Author chun
 * @Date 2019/8/29 9:52
 */
@Data
public class LoginPacket extends Packet {

    /**
     * 用户id
     */
    private String id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    @Override
    public byte getCommand() {
        return CommandVar.LOGIN_COMMAND;
    }
}
