package com.yupi.server.handler;

import com.yupi.codec.MessageCodec;
import com.yupi.common.MessageRequest;
import com.yupi.server.channel.ChannelSupervise;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 消息处理器
 * @author dhwc
 * @create 2022-10-06 16:11
 */
@Component
@Slf4j
public class CollectionHandler extends ChannelInboundHandlerAdapter {
    //TODO 可以注入一个任务接口类型的对象


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("进入服务端 {}", ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //1、解析消息
        //2、
//        ChannelSupervise.findChannel()
        log.info("进入服务端-- {}", msg);
        //TODO 通过请求的类型，以及业务类型执行相应的业务操作
        System.out.println(msg);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("噶了");
    }
}
