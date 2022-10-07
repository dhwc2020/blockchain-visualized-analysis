package com.yupi.codec;

import com.yupi.common.Message;
import com.yupi.common.MessageTypeSelector;
import io.netty.buffer.ByteBuf;
import org.springframework.core.serializer.support.SerializationFailedException;
import org.springframework.stereotype.Component;

/**
 * 消息编解码
 * @author dhwc
 * @create 2022-10-06 22:03
 */
@Component
public class MessageCodec<T extends Message> implements Codec<T>{
    //自定义一个魔数
    private static final int MAGIC_NUMBER = 0x52567726;
    //序列化器
    private final Serializer<T> SERIALIZER;

    public MessageCodec() {
        this.SERIALIZER = new KryoSerializer<>();
    }

    public MessageCodec(Serializer<T> SERIALIZER) {
        this.SERIALIZER = SERIALIZER;
    }

    @Override
    public void encode(ByteBuf byteBuf, T t) {
        // 1. 构建序列化器
        Serializer<T> serializer = new KryoSerializer<>();
        // 2. 序列化对象
        byte[] bytes = serializer.serialize(t);
        //System.out.println(Arrays.toString(bytes));
        // 3. 实际编码过程，把通信协议几个部分，一一编码
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(t.getVersion());
        byteBuf.writeByte(t.getRequest());
        byteBuf.writeByte(serializer.getSerializerAlogrithm());
        byteBuf.writeInt(bytes.length);
        System.out.println(bytes.length);
        byteBuf.writeBytes(bytes);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T decode(ByteBuf byteBuf) {
        // 跳过魔数
        byteBuf.skipBytes(4);
        // 跳过版本号
        byteBuf.skipBytes(1);
        // 请求、响应···
        byte type = byteBuf.readByte();
        // 序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();
        if(serializeAlgorithm != SERIALIZER.getSerializerAlogrithm()){
            throw new SerializationFailedException("序列化算法不一致");
        }

        // 数据包长度
        int length = byteBuf.readInt();
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        System.out.println(bytes[0]);

        return SERIALIZER.deserialize((Class<T>) MessageTypeSelector.select(type), bytes);
    }
}
