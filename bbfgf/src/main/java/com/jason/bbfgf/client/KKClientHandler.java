package com.jason.bbfgf.client;

import com.jason.bbfgf.entity.Request;
import com.jason.bbfgf.entity.Response;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketAddress;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by paji on 16/8/30
 */
public class KKClientHandler extends SimpleChannelInboundHandler<Response> {

    private static final Logger LOGGER = LoggerFactory.getLogger(KKClientHandler.class);

    private ConcurrentHashMap<String, KKFuture> pendingRPC = new ConcurrentHashMap<>();

    private volatile Channel channel;
    private SocketAddress remotePeer;

    public Channel getChannel() {
        return channel;
    }

    public SocketAddress getRemotePeer() {
        return remotePeer;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        this.remotePeer = this.channel.remoteAddress();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        this.channel = ctx.channel();
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Response response) throws Exception {
        String requestId = response.getRequestId();
        KKFuture kkFuture = pendingRPC.get(requestId);
        if (kkFuture != null) {
            pendingRPC.remove(requestId);
            kkFuture.done(response);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.error("client caught exception", cause);
        ctx.close();
    }

    public void close() {
        channel.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    public KKFuture sendRequest(Request request) {
        KKFuture kkFuture = new KKFuture(request);
        pendingRPC.put(request.getRequestId(), kkFuture);
        channel.writeAndFlush(request);

        return kkFuture;
    }

}
