package com.chun.netty.console;

import java.util.HashMap;
import java.util.Map;

/**
 * 控制台命令工厂类
 *
 * @Author chun
 * @Date 2019/9/4 14:47
 */
public class ConsoleCommandFactory {

    private static Map<String, ConsoleCommand> commandMap;

    static {
        commandMap = new HashMap<>();
        commandMap.put(ConsoleCommandVar.CREATE_GROUP, new CreateGroupConsoleCommand());
        commandMap.put(ConsoleCommandVar.SEND_MSG, new SendMsgConsoleCommand());
        commandMap.put(ConsoleCommandVar.LOGOUT, new LogoutConsoleCommand());
        commandMap.put(ConsoleCommandVar.LIST_GROUP, new ListGroupConsoleCommand());
        commandMap.put(ConsoleCommandVar.JOIN_GROUP, new JoinGroupConsoleCommand());
        commandMap.put(ConsoleCommandVar.QUIT_GROUP, new QuitGroupConsoleCommand());
        commandMap.put(ConsoleCommandVar.SEND_TO_GROUP, new SendToGroupConsoleCommand());
    }

    /**
     * 获取指令对象
     *
     * @param msg
     * @return
     */
    public static ConsoleCommand getCommand(String msg){
        ConsoleCommand command = commandMap.get(msg);
        return command != null ? commandMap.get(msg) : new NullabelConsoleCommand();
    }
}
