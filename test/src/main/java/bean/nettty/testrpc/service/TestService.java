package bean.nettty.testrpc.service;


import bean.nettty.testrpc.core.TestDecoder;
import bean.nettty.testrpc.core.TestEncoder;
import bean.nettty.testrpc.entity.TestRequest;
import bean.nettty.testrpc.entity.TestResponse;
import bean.nettty.testrpc.entity.TestViruses;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by paji on 16/8/31
 */
public class TestService implements ApplicationContextAware, InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(TestService.class);

    private static ThreadPoolExecutor threadPoolExecutor;

    private String serverAddress;

    private Map<String, Object> handlerMap = new HashMap<String, Object>();

    public TestService() {
        this.serverAddress = "127.0.0.1:8888";
    }

    public TestService(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        System.out.println("----------------------------------");

        Map<String, Object> virusesBeanMap = applicationContext.getBeansWithAnnotation(TestViruses.class);
        if (MapUtils.isNotEmpty(virusesBeanMap)) {
            for (Object virusesBean : virusesBeanMap.values()) {
                String interfaceName = virusesBean.getClass().getAnnotation(TestViruses.class).value().getName();
                handlerMap.put(interfaceName, virusesBean);
            }
        }


        System.out.println("---------------------------------"+handlerMap);
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        System.out.println("-------------------------afterPropertiesSet----------------------------------");

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline()
                                    .addLast(new LengthFieldBasedFrameDecoder(65536,0,4,0,0))
                                    .addLast(new TestDecoder(TestRequest.class))
                                    .addLast(new TestEncoder(TestResponse.class))
                                    .addLast(new TestHandler(handlerMap));
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            String[] array = serverAddress.split(":");
            String host = array[0];
            int port = Integer.parseInt(array[1]);

            System.out.println("---------------------"+port);


            ChannelFuture future = bootstrap.bind(port).sync();


            logger.info("Server started on port {}", port);

//            if (serviceRegistry != null) {
//                serviceRegistry.register(serverAddress);
//            }

            future.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void submit(Runnable task) {
        if(threadPoolExecutor == null){
            synchronized (TestService.class) {
                if(threadPoolExecutor == null){
                    threadPoolExecutor = new ThreadPoolExecutor(16, 16, 600L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(65536));
                }
            }
        }
        threadPoolExecutor.submit(task);
    }
}
