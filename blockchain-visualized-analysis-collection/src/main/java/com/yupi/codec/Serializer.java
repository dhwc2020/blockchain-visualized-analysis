package com.yupi.codec;

/**
 * @author dhwc
 * @create 2022-10-06 19:01
 */
public interface Serializer<T> {
    //序列化算法
    byte getSerializerAlogrithm();
    //java 对象转换成二进制
    byte[] serialize(T object);
    //二进制转换成 java 对象
    T deserialize(Class<T> clazz, byte[] bytes);
}
