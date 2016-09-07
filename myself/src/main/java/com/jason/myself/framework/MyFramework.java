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

import java.lang.reflect.Proxy;

/**
 * 总框架类
 * Created by paji on 16/9/7
 */
public class MyFramework {

    /**
     * 服务注册
     *
     * @param classObj  需要注册的服务对象
     * @param port 端口
     * @param ip   地址
     * @throws InterruptedException
     */
    public static void register(final Object classObj, int port, String ip) throws InterruptedException {
        int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors() * 2;

        int BIZTHREADSIZE = 100;
        EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
        EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);
        if (classObj == null)
            throw new IllegalArgumentException("对象不能为null");
        if (port <= 0 || port > 65535)
            throw new IllegalArgumentException("错误的端口" + port);
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
                pipeline.addLast(new TcpServerHandler(classObj));
            }
        });
        Channel channel = bootstrap.bind(ip, port).syncUninterruptibly().channel();
        // channel.closeFuture().sync();//TODO 此处可以单独拎出来做一个停止服务的函数
        System.out.println("TCP服务器已启动");
    }

    /**
     * 远程服务获取
     * @param interfaceClass
     * @param host
     * @param port
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T refer(Class<T> interfaceClass, final String host, final int port) {
        if (interfaceClass == null)
            throw new IllegalArgumentException("接口类型不能为空");
        if (!interfaceClass.isInterface())
            throw new IllegalArgumentException("类名" + interfaceClass.getName() + "必须是接口");
        if (host == null || host.length() == 0)
            throw new IllegalArgumentException("目标主机不能为空");
        if (port <= 0 || port > 65535)
            throw new IllegalArgumentException("端口错误：" + port);

        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new ClientInvocationHandler(host, port));

    }
}


