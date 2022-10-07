package com.yupi.codec;

import com.yupi.common.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义编码规则，后续添加到netty的处理链中
 * @author dhwc
 * @create 2022-10-07 15:31
 */
@Component
public class MessageDecoder<T extends Message> extends ByteToMessageDecoder {
    private final Codec<T> codec;

    public MessageDecoder(Codec<T> codec) {
        this.codec = codec;
    }

    @Override
    public void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        list.add(codec.decode(byteBuf));
    }
}
