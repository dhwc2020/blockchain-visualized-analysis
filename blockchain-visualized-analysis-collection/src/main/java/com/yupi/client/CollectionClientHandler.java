package com.yupi.client;

import com.yupi.codec.MessageEncoder;
import com.yupi.common.MessageRequest;
import com.yupi.constant.MessageTypeConst;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * 消息处理器
 * @author dhwc
 * @create 2022-10-06 16:11
 */
@Component
public class CollectionClientHandler extends ChannelInboundHandlerAdapter {
    private final MessageEncoder encoder;

    public CollectionClientHandler(MessageEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端发送消息...");
        // 1. 获取数据
        ByteBuf buffer = getByteBuf(ctx);
        // 2. 写数据
        ctx.channel().writeAndFlush(buffer);
    }
    private ByteBuf getByteBuf(ChannelHandlerContext ctx) throws Exception {
        // 1. 获取二进制抽象 ByteBuf
        ByteBuf buffer = ctx.alloc().directBuffer();
        // 2. 准备数据，指定字符串的字符集为 utf-8
        MessageRequest message = new MessageRequest();
        message.setMsgId(1);
        message.setVersion((byte) 1);
        message.setRequest(MessageTypeConst.REQUEST);
        message.setParams("【客户端】:这是客户端发送的消息："+new Date());
        encoder.encode(ctx, message, buffer);
        System.out.println("写入完成");
        return buffer;
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        //接收服务端的消息并打印
        System.out.println(byteBuf.toString(Charset.forName("utf-8")));
    }
}
