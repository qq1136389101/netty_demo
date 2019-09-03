package com.chun.netty.packet;

import com.chun.netty.packet.var.PacketVar;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * packet 拆包粘包处理handler
 *
 * @Author chun
 * @Date 2019/9/3 17:11
 */
public class PacketSpliter extends LengthFieldBasedFrameDecoder {

    /**
     * packet 存放数据长度的字节的开始位置的下标
     */
    private static final int LENGTH_FIELD_OFFSET = 7;

    /**
     * packet 存放数据长度的字节的长度
     */
    private static final int LENGTH_FIELD_LENGTH = 4;

    public PacketSpliter() {
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        /**
         * 判断魔术变量是否是我们自定义协议的魔术变量，不是则不对该数据做处理, 并关闭连接通道
         */
        if(in.getInt(in.readerIndex()) != PacketVar.MAGIC_NUMBER){
            ctx.channel().close();
            return null;
        }
        return super.decode(ctx, in);
    }
}
