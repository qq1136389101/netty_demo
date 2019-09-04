package com.chun.netty.packet.response;

import com.chun.netty.packet.command.var.CommandVar;
import lombok.Data;

/**
 * @Author chun
 * @Date 2019/8/29 9:52
 */
@Data
public class ListGroupResponsePacket extends CommonResponse {

    public ListGroupResponsePacket() {
        super();
    }

    public ListGroupResponsePacket(Object data) {
        super(data);
    }

    public ListGroupResponsePacket(Integer code, String msg) {
        super(code, msg);
    }

    public ListGroupResponsePacket(Integer code, String msg, Object data) {
        super(code, msg, data);
    }

    @Override
    public byte getCommand() {
        return CommandVar.LIST_GROUP;
    }
}
