package com.chun.netty.packet.request;

import com.chun.netty.packet.Packet;
import com.chun.netty.packet.command.var.CommandVar;
import lombok.Data;

/**
 * 加入群聊 请求包
 *
 * @Author chun
 * @Date 2019/8/29 9:52
 */
@Data
public class SendToGroupRequestPacket extends Packet {

    /**
     * 要加入的群的名称
     */
    private String groupName;

    /**
     * 消息内容
     */
    private String msg;

    @Override
    public byte getCommand() {
        return CommandVar.SEND_TO_GROUP;
    }
}
