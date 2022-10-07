package com.yupi.constant;

/**
 * 消息业务码
 * @author dhwc
 * @create 2022-10-06 0:04
 */
public interface MessageCodeConst {
    /**
     * 心跳
     */
    short HEART_BEAT = 1;
//    /**
//     *登录请求
//     */
//    short LOGIN_REQUEST = 2;
//    /**
//     * 登录响应
//     */
//    short LOGIN_RESPONSE = 3;
    /**
     * 获取区块
     */
    short LIST_BLOCK = 10001;
    /**
     * 获取区块链基本信息
     */
    short GET_BLOCKCHAIN = 10002;

}
