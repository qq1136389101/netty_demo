package com.chun.netty.util;

import com.chun.netty.var.AttributeVar;
import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

/**
 * @Author chun
 * @Date 2019/9/2 16:47
 */
public class LoginUtils {

    /**
     * 设置为登录状态
     *
     * @param channel
     */
    public static void login(Channel channel){
        channel.attr(AttributeVar.LOGIN).set(true);
    }

    /**
     * 设置为未登录状态
     *
     * @param channel
     */
    public static void logout(Channel channel){
        channel.attr(AttributeVar.LOGIN).set(false);
    }

    /**
     * 判断是否登入
     *
     * @param channel
     * @return
     */
    public static boolean hasLogin(Channel channel){
        Attribute<Boolean> attribute = channel.attr(AttributeVar.LOGIN);
        if(attribute.get() != null && attribute.get()){
            return true;
        }else{
            return false;
        }
    }
}
