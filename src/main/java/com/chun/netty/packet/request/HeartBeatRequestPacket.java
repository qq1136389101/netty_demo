package com.chun.netty.packet.request;

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
public class HeartBeatRequestPacket extends Packet {
    @Override
    public byte getCommand() {
        return CommandVar.HEART_BEAT;
    }
}
