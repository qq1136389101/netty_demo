package com.chun.netty.packet.response;

import com.chun.netty.packet.command.var.CommandVar;
import lombok.Data;

/**
 * @Author chun
 * @Date 2019/8/29 9:52
 */
@Data
public class JoinGroupResponsePacket extends CommonResponse {

    public JoinGroupResponsePacket() {
        super();
    }

    public JoinGroupResponsePacket(Object data) {
        super(data);
    }

    public JoinGroupResponsePacket(Integer code, String msg) {
        super(code, msg);
    }

    public JoinGroupResponsePacket(Integer code, String msg, Object data) {
        super(code, msg, data);
    }

    @Override
    public byte getCommand() {
        return CommandVar.JOIN_GROUP;
    }
}
