package com.yupi.config;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注入spring容器，由spring统一管理
 * @author dhwc
 * @create 2022-10-06 16:18
 */
@Configuration
public class CollectionServerConfig {

    @Bean(name = "bossGroup")
    public EventLoopGroup boosGroup(){
        return new NioEventLoopGroup();
    }

    @Bean(name = "workerGroup")
    public EventLoopGroup workerGroup(){
        return new NioEventLoopGroup();
    }

    @Bean(name = "serverBootstrap")
    public ServerBootstrap serverBootstrap(EventLoopGroup bossGroup, EventLoopGroup workerGroup){
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class);
        return serverBootstrap;
    }

    @Bean(name = "lengthFieldBasedFrameDecoder")
    public LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder(){
        //解决拆包和粘包问题
        return new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4);
    }
}
