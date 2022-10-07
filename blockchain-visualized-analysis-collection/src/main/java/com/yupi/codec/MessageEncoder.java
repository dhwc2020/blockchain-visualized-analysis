package com.yupi.codec;

import com.yupi.common.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.springframework.stereotype.Component;

/**
 * 自定义编码规则，后续添加到netty的处理链中
 * @author dhwc
 * @create 2022-10-07 15:12
 */
@Component
public class MessageEncoder extends MessageToByteEncoder<Message>{
    private final Codec<Message> codec;

    public MessageEncoder(Codec<Message> codec) {
        this.codec = codec;
    }

    @Override
    public void encode(ChannelHandlerContext channelHandlerContext, Message message, ByteBuf byteBuf) throws Exception {
        codec.encode(byteBuf, message);
    }
}
