package com.chun.netty.console;

import com.chun.netty.packet.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author chun
 * @Date 2019/9/4 14:36
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("请输入要加入群聊的用户名,逗号隔开:");
        String[] userNames = scanner.nextLine().split(",");

        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();
        createGroupRequestPacket.setUserNames(userNames);
        channel.writeAndFlush(createGroupRequestPacket);
    }
}
