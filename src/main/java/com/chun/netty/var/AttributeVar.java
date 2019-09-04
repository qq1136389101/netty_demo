package com.chun.netty.var;

import com.chun.netty.util.Session;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

/**
 * channel 属性类
 *
 * @Author chun
 * @Date 2019/9/2 16:55
 */
public class AttributeVar {
    /**
     * 是否登录
     */
    public static final AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    /**
     * 用户登录信息
     */
    public static final AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
