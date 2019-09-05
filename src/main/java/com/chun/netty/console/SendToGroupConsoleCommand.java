package com.chun.netty.console;

import com.chun.netty.packet.request.JoinGroupRequestPacket;
import com.chun.netty.packet.request.SendToGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author chun
 * @Date 2019/9/4 14:36
 */
public class SendToGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("请输入群名:");
        String groupName = scanner.nextLine();
        System.out.print("请输入消息内容:");
        String msg = scanner.nextLine();

        SendToGroupRequestPacket sendToGroupRequestPacket = new SendToGroupRequestPacket();
        sendToGroupRequestPacket.setGroupName(groupName);
        sendToGroupRequestPacket.setMsg(msg);
        channel.writeAndFlush(sendToGroupRequestPacket);
    }
}
