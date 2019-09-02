package com.chun.netty.packet;

/**
 * 自定义的包协议
 * 1. 魔术    4字节
 * 2. 版本号   1字节
 * 3. 序列化算法 1字节
 * 4. 指令    1字节
 * 5. 数据长度  4字节
 * 6. 数据    n字节
 */
public abstract class Packet {

    /**
    * 获取指令
    */
    public abstract byte getCommand();

}