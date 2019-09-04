package com.chun.netty.packet.request;

import com.chun.netty.packet.Packet;
import com.chun.netty.packet.command.var.CommandVar;
import lombok.Data;

/**
 * @Author chun
 * @Date 2019/8/29 9:52
 */
@Data
public class LogoutRequestPacket extends Packet {

    @Override
    public byte getCommand() {
        return CommandVar.LOGOUT_COMMAND;
    }
}
