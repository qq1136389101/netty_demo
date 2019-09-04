package com.chun.netty.packet.response;

import com.chun.netty.packet.Packet;
import com.chun.netty.packet.command.var.CommandVar;
import lombok.Data;

/**
 * @Author chun
 * @Date 2019/8/29 9:52
 */
@Data
public class CreateGroupResponsePacket extends CommonResponse {

    public CreateGroupResponsePacket() {
        super();
    }

    public CreateGroupResponsePacket(Object data) {
        super(data);
    }

    public CreateGroupResponsePacket(Integer code, String msg) {
        super(code, msg);
    }

    public CreateGroupResponsePacket(Integer code, String msg, Object data) {
        super(code, msg, data);
    }

    @Override
    public byte getCommand() {
        return CommandVar.CREATE_GROUP_COMMAND;
    }
}
