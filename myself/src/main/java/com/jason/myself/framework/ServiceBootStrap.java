package com.jason.myself.framework;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * Created by liuzhixin on 2016/10/14.
 */
public class ServiceBootStrap {

    private static ServiceBootStrap serviceBootStrap = new ServiceBootStrap();

    private ServiceBootStrap(){
    }

    public static ServiceBootStrap getInstance(){
        return serviceBootStrap;
    }

    public void startFramework(String ip , int port ){

        int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors();
        int BIZTHREADSIZE = Runtime.getRuntime().availableProcessors() * 4;
        EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
        EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel ch) throws Exception {
                // TODO Auto-generated method stub
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                pipeline.addLast(new LengthFieldPrepender(4));
                pipeline.addLast("encoder", new ObjectEncoder());
                pipeline.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
                // pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                // pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                pipeline.addLast(new TcpServerHandler());
            }
        });
        Channel channel = bootstrap.bind(ip, port).syncUninterruptibly().channel();
        // channel.closeFuture().sync();//TODO 此处可以单独拎出来做一个停止服务的函数
        System.out.println("TCP服务器已启动");
    }


}
