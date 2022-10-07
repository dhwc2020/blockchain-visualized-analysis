package com.yupi.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 数据采集服务器
 * @author dhwc
 * @create 2022-10-06 15:32
 */
@Component
@Slf4j
public class CollectionServer {
    @Value("${collection.server.port}")
    private int port;
    @Value("${collection.server.count}")
    private int count;
    private ChannelFuture server;
    /**
     * 处理客户端建立链接
     */
    private final EventLoopGroup bossGroup;
    /**
     * 处理与客户端交互的数据
     */
    private final EventLoopGroup workerGroup;
    /**
     * 服务器配置类
     */
    private final ServerBootstrap serverBootstrap;
    /**
     * 通道初始化器
     */
    private final ChannelInitializer<NioSocketChannel> channel;

    public CollectionServer(@Qualifier("bossGroup") EventLoopGroup bossGroup,
                            @Qualifier("workerGroup") EventLoopGroup workerGroup,
                            @Qualifier("serverBootstrap") ServerBootstrap serverBootstrap,
                            @Qualifier("collectionInitChannel") ChannelInitializer<NioSocketChannel> channel) {
        this.bossGroup = bossGroup;
        this.workerGroup = workerGroup;
        this.serverBootstrap = serverBootstrap;
        this.channel = channel;
    }

    @PostConstruct
    private void init(){
        serverBootstrap.childHandler(channel)
        .option(ChannelOption.SO_BACKLOG, count)          // (5)
        .childOption(ChannelOption.SO_KEEPALIVE, true);
    }

    public void start(){
        try {
            server = serverBootstrap.bind(port).sync();
            log.info("CollectionServer is running, port is {}", port);
            //监听到关闭事件继续执行，否则阻塞
            server.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("CollectionServer start fail", e);
        }finally {
            stop();
        }
    }

    public void stop(){
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
        server.channel().close();
        log.info("CollectionServer stop success");
    }
}
