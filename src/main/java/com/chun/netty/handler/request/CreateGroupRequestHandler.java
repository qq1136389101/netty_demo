package com.chun.netty.handler.request;

import com.chun.netty.packet.request.CreateGroupRequestPacket;
import com.chun.netty.packet.request.MessageRequestPacket;
import com.chun.netty.packet.response.CreateGroupResponsePacket;
import com.chun.netty.packet.response.MessageResponsePacket;
import com.chun.netty.util.Session;
import com.chun.netty.util.SessionUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 创建群聊
 *
 * @Author chun
 * @Date 2019/9/3 11:11
 */
@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    // 单例
    public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        String groupName = createGroupRequestPacket.getGroupName();
        List<String> userNames = new ArrayList(Arrays.asList(createGroupRequestPacket.getUserNames()));

        // 创建 channelGroup 分组
        ChannelGroup channels = new DefaultChannelGroup(channelHandlerContext.executor());
        for (int i = 0; i < userNames.size(); i++) {
            Channel channel = SessionUtils.getChannelByUserName(userNames.get(i));
            if(channel != null){
                channels.add(channel);
            }else{
                userNames.remove(userNames.get(i));
            }
        }

        // 保存群信息
        SessionUtils.addChannelGroup(groupName, channels);

        // 创建群聊后通知所有群聊里的用户
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setCode(200);
        createGroupResponsePacket.setMsg("已加入群聊,成员【" + String.join(",", userNames) + "】");
        channels.writeAndFlush(createGroupResponsePacket);

        System.out.println("创建群聊成功, 成员【" + String.join(",", userNames) + "】");
    }
}
