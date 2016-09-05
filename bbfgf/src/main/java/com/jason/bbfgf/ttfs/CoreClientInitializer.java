package com.jason.bbfgf.ttfs;

import com.jason.bbfgf.encode.KKDecoder;
import com.jason.bbfgf.encode.KKEncoder;
import com.jason.bbfgf.entity.Request;
import com.jason.bbfgf.entity.Response;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;



/**
 * Created by paji on 16/9/5
 */
public class CoreClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline cp = socketChannel.pipeline();
        cp.addLast(new KKEncoder(Request.class));
        cp.addLast(new LengthFieldBasedFrameDecoder(655, 0, 4, 0, 0));
        cp.addLast(new KKDecoder(Response.class));
        cp.addLast(new CoreClientHandler());
    }
}
