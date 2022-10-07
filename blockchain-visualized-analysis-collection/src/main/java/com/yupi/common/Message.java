package com.yupi.common;

import lombok.Data;

/**
 * 协议版本号
 * @author dhwc
 * @create 2022-10-06 22:30
 */
@Data
public class Message {
    /**
     * 版本号
     */
    private byte version;
    /**
     * 请求还是响应，还是其他
     */
    private byte request;
    /**
     * 消息id
     */
    private long msgId;
}
