package com.yupi.common;

import com.yupi.constant.MessageTypeConst;

/**
 * @author dhwc
 * @create 2022-10-07 21:54
 */
public interface MessageTypeSelector {
    static Class<? extends Message> select(byte ch){
        switch (ch){
            case MessageTypeConst.REQUEST:
                return MessageRequest.class;
            case MessageTypeConst.RESPONSE:
                return MessageResponse.class;
        }
        return null;
    }
}
