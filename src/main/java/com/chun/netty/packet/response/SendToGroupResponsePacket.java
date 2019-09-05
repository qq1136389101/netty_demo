package com.chun.netty.packet.response;

import com.chun.netty.packet.command.var.CommandVar;
import lombok.Data;

/**
 * @Author chun
 * @Date 2019/8/29 9:52
 */
@Data
public class SendToGroupResponsePacket extends CommonResponse {

    public SendToGroupResponsePacket() {
        super();
    }

    public SendToGroupResponsePacket(Object data) {
        super(data);
    }

    public SendToGroupResponsePacket(Integer code, String msg) {
        super(code, msg);
    }

    public SendToGroupResponsePacket(Integer code, String msg, Object data) {
        super(code, msg, data);
    }

    @Override
    public byte getCommand() {
        return CommandVar.SEND_TO_GROUP;
    }
}
