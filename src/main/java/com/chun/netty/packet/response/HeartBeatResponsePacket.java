package com.chun.netty.packet.response;

import com.chun.netty.packet.Packet;
import com.chun.netty.packet.command.var.CommandVar;
import lombok.Data;

/**
 * 心跳检查 请求包
 *
 * @Author chun
 * @Date 2019/8/29 9:52
 */
@Data
public class HeartBeatResponsePacket extends CommonResponse {

    public HeartBeatResponsePacket() {
        super();
    }

    public HeartBeatResponsePacket(Object data) {
        super(data);
    }

    public HeartBeatResponsePacket(Integer code, String msg) {
        super(code, msg);
    }

    public HeartBeatResponsePacket(Integer code, String msg, Object data) {
        super(code, msg, data);
    }

    @Override
    public byte getCommand() {
        return CommandVar.HEART_BEAT;
    }
}
