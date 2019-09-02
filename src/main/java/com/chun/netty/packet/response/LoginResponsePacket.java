package com.chun.netty.packet.response;

import com.chun.netty.packet.command.var.CommandVar;
import lombok.Data;

/**
 * @Author chun
 * @Date 2019/8/29 9:52
 */
@Data
public class LoginResponsePacket extends CommonResponse {

    public LoginResponsePacket() {
        super();
    }

    public LoginResponsePacket(Object data) {
        super(data);
    }

    public LoginResponsePacket(Integer code, String msg) {
        super(code, msg);
    }

    public LoginResponsePacket(Integer code, String msg, Object data) {
        super(code, msg, data);
    }

    @Override
    public byte getCommand() {
        return CommandVar.LOGIN_COMMAND;
    }
}
