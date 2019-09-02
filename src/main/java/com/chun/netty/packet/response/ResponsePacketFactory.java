package com.chun.netty.packet.response;

import com.chun.netty.packet.command.var.CommandVar;

/**
 * @Author chun
 * @Date 2019/9/2 14:46
 */
public class ResponsePacketFactory {

    public static CommonResponse getResponse(byte command){
        switch (command){
            case CommandVar.LOGIN_COMMAND:
                return null;
        }

        return null;
    }
}
