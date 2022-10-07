package com.yupi.server.channel;

import com.yupi.codec.MessageDecoder;
import com.yupi.codec.MessageEncoder;
import com.yupi.common.Message;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * channel进行初始化
 * @author dhwc
 * @create 2022-10-06 16:57
 */
@Component
public class CollectionInitChannel<T extends NioSocketChannel, K extends Message> extends ChannelInitializer<T> {
    private final ChannelInboundHandlerAdapter handler;
    private final LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder;
    private final MessageEncoder encoder;
    private final MessageDecoder<K> decoder;

    public CollectionInitChannel(@Qualifier("collectionHandler") ChannelInboundHandlerAdapter handler,
                                 LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder,
                                 MessageEncoder encoder,
                                 MessageDecoder<K> decoder) {
        this.handler = handler;
        this.lengthFieldBasedFrameDecoder = lengthFieldBasedFrameDecoder;
        this.encoder = encoder;
        this.decoder = decoder;
    }

    protected void initChannel(T t) throws Exception {
        //绑定处理器
        t.pipeline().addLast(lengthFieldBasedFrameDecoder);

        t.pipeline().addLast(decoder);
        t.pipeline().addLast(handler);
        t.pipeline().addLast(encoder);
    }
}
