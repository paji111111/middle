package com.jason.bbfgf.province;

import com.jason.bbfgf.client.KKClientHandler;
import com.jason.bbfgf.encode.KKDecoder;
import com.jason.bbfgf.encode.KKEncoder;
import com.jason.bbfgf.entity.Request;
import com.jason.bbfgf.entity.Response;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Created by paji on 16/9/1
 */
public class PPConnectionClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    public void initChannel(SocketChannel channel) throws Exception {

        ChannelPipeline pipeline = channel.pipeline();

        pipeline.addLast(new KKEncoder(Request.class));
        pipeline.addLast(new LengthFieldBasedFrameDecoder(655, 0, 4, 0, 0));
        pipeline.addLast(new KKDecoder(Response.class));
        pipeline.addLast(new PPConnectionClientHandler());
    }
}
