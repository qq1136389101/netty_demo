package com.chun.netty.util;

import com.chun.netty.packet.Packet;
import com.chun.netty.packet.command.var.CommandTypeVar;
import com.chun.netty.packet.var.PacketVar;
import com.chun.netty.serializer.Serializer;
import com.chun.netty.serializer.SerializerFactory;
import com.chun.netty.serializer.var.SerializerAlgorithmVar;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @Author chun
 * @Date 2019/8/29 9:57
 */
public class PacketUtils {

    /**
     * 默认编码方式
     */
    private static final byte DEFAULT_SERIALIZER = SerializerAlgorithmVar.JSON;

    /**
     * 默认编码
     *
     * @param packet
     * @return
     */
    public ByteBuf encode(Packet packet){
       return encode(packet, SerializerFactory.getSerializer(DEFAULT_SERIALIZER));
    }

    /**
     * 编码
     *
     * @param packet    包对象
     * @param serializer    序列化方式
     * @return
     */
    public static ByteBuf encode(Packet packet, Serializer serializer){
        // 通过 packet 中的算法将 packet 转换为 byte[]
        byte[] bytes = serializer.serialize(packet);

        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        byteBuf.writeInt(PacketVar.MAGIC_NUMBER);
        byteBuf.writeByte(PacketVar.VERSION);
        byteBuf.writeByte(serializer.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    /**
     * 解码
     *
     * @param byteBuf   字节码
     * @return
     */
    public static Packet decode(ByteBuf byteBuf, int commandType){
        // 跳过魔术变量
        byteBuf.skipBytes(4);

        // 跳过版本号
        byteBuf.skipBytes(1);

        // 序列化方法
        byte serializerAlgorithm = byteBuf.readByte();

        // 指令
        byte command = byteBuf.readByte();

        // 数据包长度
        int length = byteBuf.readInt();

        // 数据
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Serializer serializer = SerializerFactory.getSerializer(serializerAlgorithm);
        Class<? extends Packet> clazz = getPacketClass(command, commandType);
        if(serializer != null && clazz != null){
            return serializer.deserialize(clazz, bytes);
        }
        return null;
    }

    /**
     * 根据指令获取对应的处理类
     *
     * @param command   指令
     * @return
     */
    public static Class<? extends Packet> getPacketClass(byte command, int commondType){
        if(commondType == CommandTypeVar.REQUEST){
            return (Class) PacketVar.PACKET_CLASS.get(command);
        }else{
            return (Class) PacketVar.RESPONSE_PACKET_CLASS.get(command);
        }
    }
}
