package com.yupi.server.channel;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 保存所有在线channel(客户端信息)
 *
 * @author dhwc
 * @create 2022-10-07 17:49
 */
public class ChannelSupervise {
    private static final ChannelGroup GLOBAL_GROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static final ConcurrentMap<String, ChannelId> NAME_CHANNEL_MAP = new ConcurrentHashMap<>();
    private static final ConcurrentMap<ChannelId, String> CHANNEL_NAME_MAP = new ConcurrentHashMap<>();

    public static void addChannel(Channel channel, String name) {
        GLOBAL_GROUP.add(channel);
        NAME_CHANNEL_MAP.put(name, channel.id());
        CHANNEL_NAME_MAP.put(channel.id(), name);
    }

    public static void removeChannel(Channel channel) {
        GLOBAL_GROUP.remove(channel);
        NAME_CHANNEL_MAP.remove(CHANNEL_NAME_MAP.get(channel.id()));
        CHANNEL_NAME_MAP.remove(channel.id());
    }

    public static Channel findChannel(String name) {
        return GLOBAL_GROUP.find(NAME_CHANNEL_MAP.get(name));
    }

    public static void send2All(TextWebSocketFrame tws) {
        GLOBAL_GROUP.writeAndFlush(tws);
    }
}
