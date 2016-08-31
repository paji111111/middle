package com.jason.bbfgf.server;

import com.jason.bbfgf.annotation.Producer;
import com.jason.bbfgf.encode.KKDecoder;
import com.jason.bbfgf.encode.KKEncoder;
import com.jason.bbfgf.entity.Request;
import com.jason.bbfgf.entity.Response;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/** 注解服务启动
 * Created by paji on 16/8/31
 */
public class AnnotationServerBoot implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(AnnotationServerBoot.class);

    private static ThreadPoolExecutor threadPoolExecutor;

    private final Integer port;

    private Map<String, Object> handlerMap = new HashMap<String, Object>();

    public AnnotationServerBoot(Integer port) {
        this.port = port;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("----------------------------------");

        Map<String, Object> producerBeanMap = applicationContext.getBeansWithAnnotation(Producer.class);
        if (MapUtils.isNotEmpty(producerBeanMap)) {
            for (Object producerBean : producerBeanMap.values()) {
                String interfaceName = producerBean.getClass().getAnnotation(Producer.class).value().getName();
                handlerMap.put(interfaceName, producerBean);
            }
        }

        System.out.println("---------------------------------"+handlerMap);
    }


    public void init() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline()
                                    .addLast(new LengthFieldBasedFrameDecoder(65536,0,4,0,0))
                                    .addLast(new KKDecoder(Request.class))
                                    .addLast(new KKEncoder(Response.class))
                                    .addLast(new KKHandler(handlerMap));
                        }
                    });
            b.bind(port).syncUninterruptibly().channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


    public static void submit(Runnable runnable){
        if(threadPoolExecutor == null){
            synchronized (AnnotationServerBoot.class) {
                if(threadPoolExecutor == null){
                    threadPoolExecutor = new ThreadPoolExecutor(16, 16, 600L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(65536));
                }
            }
        }
        threadPoolExecutor.submit(runnable);
    }

}
