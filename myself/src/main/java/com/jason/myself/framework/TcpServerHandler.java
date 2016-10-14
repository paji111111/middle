package com.jason.myself.framework;

import com.jason.myself.transport.MethodAndArgs;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;

/**
 * Created by paji on 16/9/7
 */
public class TcpServerHandler extends ChannelInboundHandlerAdapter {

    private Object response;

    public TcpServerHandler() {
        super();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        MethodAndArgs methodAndArgs = (MethodAndArgs) msg;

        Object classObj = MyFramework.serviceMapStore.get(methodAndArgs.getInterfaceName());

        Method method = classObj.getClass().getMethod(methodAndArgs.getMethodName(), methodAndArgs.getTypes());

        ctx.channel().writeAndFlush(method.invoke(classObj, methodAndArgs.getArgs()));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client die");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive>>>>>>>>");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("服务器异常");
    }
}