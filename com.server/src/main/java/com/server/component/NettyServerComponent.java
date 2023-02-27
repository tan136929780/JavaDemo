package com.server.component;

import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NettyServerComponent {

    @Value("${server.netty.port}")
    private int port;
    private final EventLoopGroup mainGroup = new NioEventLoopGroup();
    private final EventLoopGroup subGroup = new NioEventLoopGroup();
    private final ServerBootstrap serverBootstrap = new ServerBootstrap();

    public void run() {
        // try {
        //     serverBootstrap.group(mainGroup, subGroup);
        //     serverBootstrap.channel(NioServerSocketChannel.class);
        //     ChannelFutureserverBootstrap.bind(port).sync();
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // } finally {
        //     mainGroup.shutdownGracefully();
        //     mainGroup.shutdownGracefully();
        // }
    }
}