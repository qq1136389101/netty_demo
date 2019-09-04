package com.chun.netty.console;

import com.chun.netty.packet.request.CreateGroupRequestPacket;
import com.chun.netty.packet.request.ListGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author chun
 * @Date 2019/9/4 14:36
 */
public class ListGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("请输入群的名称:");
        String groupName = scanner.nextLine();

        ListGroupRequestPacket listGroupRequestPacket = new ListGroupRequestPacket();
        listGroupRequestPacket.setGroupName(groupName);
        channel.writeAndFlush(listGroupRequestPacket);
    }
}
