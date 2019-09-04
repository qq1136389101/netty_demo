package com.chun.netty.packet.request;

import com.chun.netty.packet.Packet;
import com.chun.netty.packet.command.var.CommandVar;
import lombok.Data;

/**
 * @Author chun
 * @Date 2019/8/29 9:52
 */
@Data
public class ListGroupRequestPacket extends Packet {

    /**
     * 群名
     */
    private String groupName;


    @Override
    public byte getCommand() {
        return CommandVar.LIST_GROUP;
    }
}
