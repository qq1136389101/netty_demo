package com.chun.netty.console;

import com.chun.netty.packet.request.LogoutRequestPacket;
import com.chun.netty.packet.request.MessageRequestPacket;
import com.chun.netty.util.SessionUtils;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 发送消息
 *
 * @Author chun
 * @Date 2019/9/4 14:36
 */
public class LogoutConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
        channel.writeAndFlush(logoutRequestPacket);
    }
}
