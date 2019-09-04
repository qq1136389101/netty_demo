package com.chun.netty.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author chun
 * @Date 2019/9/4 14:34
 */
public interface ConsoleCommand {

    void exec(Scanner scanner, Channel channel);
}
