package com.chun.netty.packet.command;

import com.chun.netty.packet.command.var.CommandTypeVar;
import com.chun.netty.packet.command.var.CommandVar;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author chun
 * @Date 2019/8/28 16:39
 */
public class CommandFactory {

    private static final Map<Byte, Command> commandMap = new HashMap<>();

    static {
        commandMap.put(CommandVar.LOGIN_COMMAND, new LoginCommand());
        commandMap.put(CommandVar.MESSAGE_COMMAND, new MessageCommand());
    }

    /**
     * 登录命令
     */
    private static LoginCommand loginCommand = new LoginCommand();

    /**
     * 消息命令
     */
    private static MessageCommand messageCommand = new MessageCommand();

    /**
     * 根据指令获取对应的命令
     *
     * @param command   指令码
     * @return
     */
    public static Command getCommand(byte command){
        return commandMap.get(command);
    }

}
