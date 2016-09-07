package com.jason.myself.framework;

import com.jason.myself.transport.MethodAndArgs;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.apache.commons.collections4.CollectionUtils;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/** 客户端处理逻辑
 * Created by paji on 16/9/7
 */
public class ClientInvocationHandler implements InvocationHandler {


    final String host;
    final int port;
    Channel channel;
    final TcpClientHandler tcpClientHandler = new TcpClientHandler();

    public ClientInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;

        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group);
        b.channel(NioSocketChannel.class).option(ChannelOption.SO_KEEPALIVE, true);

        b.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));

                pipeline.addLast("encoder", new ObjectEncoder());
                pipeline.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));

                pipeline.addLast("handler", tcpClientHandler);
            }
        });

        this.channel = b.connect(host, port).syncUninterruptibly().channel();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodAndArgs mArgs = new MethodAndArgs(method.getName(), method.getParameterTypes(), args);

        try {
            channel.writeAndFlush(mArgs) ;//TODO 此处有猫腻

//            channel.closeFuture().sync();
//            channel.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            group.shutdownGracefully();
        }

        System.out.println("==========");

        while (true){
            if (CollectionUtils.isNotEmpty(StoreUtil.storeResult)){
                Object object = StoreUtil.storeResult.remove(0);
                if (object != null){
                    return object;
                }
            }
            Thread.sleep(100);
        }
    }
}
