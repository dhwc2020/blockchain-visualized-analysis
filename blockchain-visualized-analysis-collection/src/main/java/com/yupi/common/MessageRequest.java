package com.yupi.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义请求协议
 * @author dhwc
 * @create 2022-10-06 0:09
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageRequest extends Message{
    /**
     * 消息业务类型
     */
    private int messageType;
    /**
     * 请求参数
     */
    private Object params;
}
