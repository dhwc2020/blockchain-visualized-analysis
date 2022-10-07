package com.yupi.codec;

import io.netty.buffer.ByteBuf;

/**
 * 编解码
 * @author dhwc
 * @create 2022-10-06 21:45
 */
public interface Codec<T> {
    void encode(ByteBuf byteBuf, T t);
    T decode(ByteBuf byteBuf);
}
