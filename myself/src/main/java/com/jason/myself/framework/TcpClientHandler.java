package com.jason.myself.framework;

import com.jason.myself.transport.MethodAndArgs;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by paji on 16/9/7
 */
public class TcpClientHandler extends SimpleChannelInboundHandler {

    Map<String,MethodAndArgs> map = new ConcurrentHashMap<>();

    @Override
    public void channelRead0(final ChannelHandlerContext ctx, final Object msg) throws Exception {

        StoreUtil.storeResult.add(msg);  //将数据转出来

        System.out.println("client接收到服务器返回的消息:" + msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        System.out.println("client exception is general");
        cause.printStackTrace();
    }

    public void send(final Channel channel , MethodAndArgs methodAndArgs){
        String requestId = String.valueOf(System.currentTimeMillis());
        methodAndArgs.setRequestId(requestId);

        if( map.put(requestId,methodAndArgs) != null ){
            channel.writeAndFlush(methodAndArgs);
        }
    }

}
