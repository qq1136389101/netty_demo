package com.chun.netty.console;

import com.chun.netty.packet.request.CreateGroupRequestPacket;
import com.chun.netty.packet.request.MessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 发送消息
 *
 * @Author chun
 * @Date 2019/9/4 14:36
 */
public class SendMsgConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("请输入要发送的消息,接收人+空格+消息:");
        String msg = scanner.nextLine();

        MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
        messageRequestPacket.setMessage(msg);
        channel.writeAndFlush(messageRequestPacket);
    }
}
