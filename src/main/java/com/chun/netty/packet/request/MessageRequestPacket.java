package com.chun.netty.packet.request;

import com.chun.netty.packet.Packet;
import com.chun.netty.packet.command.var.CommandVar;
import lombok.Data;

/**
 * @Author chun
 * @Date 2019/9/2 17:09
 */
@Data
public class MessageRequestPacket extends Packet {

    /**
     * 消息内容
     */
    private String message;

    @Override
    public byte getCommand() {
        return CommandVar.MESSAGE_COMMAND;
    }
}
