package com.chun.netty.console;

import com.chun.netty.packet.request.JoinGroupRequestPacket;
import com.chun.netty.packet.request.QuitGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author chun
 * @Date 2019/9/4 14:36
 */
public class QuitGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("请输入要退出的群的名称:");
        String groupName = scanner.nextLine();

        QuitGroupRequestPacket quitGroupRequestPacket = new QuitGroupRequestPacket();
        quitGroupRequestPacket.setGroupName(groupName);
        channel.writeAndFlush(quitGroupRequestPacket);
    }
}
