package com.github.sync;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author peach
 * @since 2020/12/4 16:32
 */
public class SyncServer {

    public static void main(String[] args) throws InterruptedException, IOException {

        //程序运行端口
        int proxyPort = Integer.parseInt("8888");

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            /**
             * 监听外网转发端口
             */
            ServerBootstrap outerServerBootstrap = new ServerBootstrap();
            outerServerBootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    //解决粘包拆包问题，initialBytesToStrip跳过的字节是我们定义的数据总长度，该类通过总长度可以解决粘包拆包为题
                    pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                    pipeline.addLast(new IdleStateHandler(5,7,3, TimeUnit.SECONDS));
                }
            });
            ChannelFuture outerChannelFuture = outerServerBootstrap.bind(proxyPort).sync();
            System.err.println("外网转发端口已开启，监听 -> " + proxyPort);

            // 开启客户端扫描线程，定时扫描下线客户端

            outerChannelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}