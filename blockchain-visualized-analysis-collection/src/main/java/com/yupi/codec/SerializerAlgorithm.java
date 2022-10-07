package com.yupi.codec;

/**
 * 序列化方式
 * @author dhwc
 * @create 2022-10-06 22:36
 */
public interface SerializerAlgorithm {
    /**
     * json方式
     */
    byte JSON = 1;
    /**
     * 二进制方式
     */
    byte BINARY = 2;
}
