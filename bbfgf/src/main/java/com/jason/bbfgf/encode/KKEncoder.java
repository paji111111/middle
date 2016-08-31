package com.jason.bbfgf.encode;

import com.jason.bbfgf.util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by paji on 16/8/31
 */
public class KKEncoder extends MessageToByteEncoder{

    private Class<?> responseClass ;
    public KKEncoder(Class<?> responseClass) {
        this.responseClass = responseClass;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        if (responseClass.isInstance(msg)) {
            byte[] data = SerializationUtil.serialize(msg);
            //byte[] data = JsonUtil.serialize(in); // Not use this, have some bugs
            out.writeInt(data.length);
            out.writeBytes(data);
        }
    }
}
