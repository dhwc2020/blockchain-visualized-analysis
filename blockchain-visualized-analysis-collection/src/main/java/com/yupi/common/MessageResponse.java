package com.yupi.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义响应协议
 * @author dhwc
 * @create 2022-10-06 0:09
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageResponse extends Message{
    /**
     * 响应码
     */
    private int code;
    /**
     * 响应描述
     */
    private String msg;
    /**
     * 响应体
     */
    private Object body;
}
