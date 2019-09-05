package com.chun.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @Author chun
 * @Date 2019/9/5 12:08
 */
public class IMIdleStateHandler extends IdleStateHandler {

    private static final int READER_IDLE_TIME_SECONDS = 15;

    public IMIdleStateHandler() {
        super(READER_IDLE_TIME_SECONDS, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        System.out.println(READER_IDLE_TIME_SECONDS +"秒未接收到数据, 关闭连接");
        ctx.channel().close();
    }
}
