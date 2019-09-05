package com.chun.netty.console;

import com.chun.netty.packet.request.JoinGroupRequestPacket;
import com.chun.netty.packet.request.ListGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author chun
 * @Date 2019/9/4 14:36
 */
public class JoinGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("请输入要加入的群的名称:");
        String groupName = scanner.nextLine();

        JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket();
        joinGroupRequestPacket.setGroupName(groupName);
        channel.writeAndFlush(joinGroupRequestPacket);
    }
}
