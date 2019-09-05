package com.chun.netty.handler.request;

import com.chun.netty.packet.request.JoinGroupRequestPacket;
import com.chun.netty.packet.request.QuitGroupRequestPacket;
import com.chun.netty.packet.response.JoinGroupResponsePacket;
import com.chun.netty.packet.response.QuitGroupResponsePacket;
import com.chun.netty.util.SessionUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * 登录
 *
 * @Author chun
 * @Date 2019/9/3 11:11
 */
@ChannelHandler.Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {

    // 单例
    public static final QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, QuitGroupRequestPacket quitGroupRequestPacket) throws Exception {
        // 获取参数
        String groupName = quitGroupRequestPacket.getGroupName();

        // 处理请求
        ChannelGroup channels = SessionUtils.getChannelGroupByGroupName(groupName);
        if(channels != null && channels.contains(channelHandlerContext.channel())){
            channels.remove(channelHandlerContext.channel());
            // 发送响应
            QuitGroupResponsePacket quitGroupResponsePacket = new QuitGroupResponsePacket(200, "退出群【" + groupName + "】成功");
            channelHandlerContext.channel().writeAndFlush(quitGroupResponsePacket);
        }else{
            QuitGroupResponsePacket quitGroupResponsePacket = new QuitGroupResponsePacket(200, "未加入群【" + groupName + "】");
            channelHandlerContext.channel().writeAndFlush(quitGroupResponsePacket);
        }
    }
}
