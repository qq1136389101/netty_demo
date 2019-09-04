package com.chun.netty.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author chun
 * @Date 2019/9/4 14:52
 */
public class NullabelConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("命令不存在");
    }
}
