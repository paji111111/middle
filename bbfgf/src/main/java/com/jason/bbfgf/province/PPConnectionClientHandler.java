package com.jason.bbfgf.province;

import com.jason.bbfgf.client.KKFuture;
import com.jason.bbfgf.entity.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

/**
 * Created by paji on 16/9/1
 */
public class PPConnectionClientHandler extends SimpleChannelInboundHandler<Response> {

    private ConcurrentHashMap<String, Future> pendingRPC = new ConcurrentHashMap<>();

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Response response) throws Exception {
        String requestId = response.getRequestId();
        Future future = pendingRPC.get(requestId);
        if (future != null) {
            pendingRPC.remove(requestId);
            future.done(response);
        }
    }
}
