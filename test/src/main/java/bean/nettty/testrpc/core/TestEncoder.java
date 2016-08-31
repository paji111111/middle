package bean.nettty.testrpc.core;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by paji on 16/8/31
 */
public class TestEncoder extends MessageToByteEncoder {

    private Class<?> encodeClass;

    public TestEncoder(Class<?> encodeClass) {
        this.encodeClass = encodeClass;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        if (encodeClass.isInstance(msg)) {
            byte[] data = SerializationUtil.serialize(msg);
            //byte[] data = JsonUtil.serialize(in); // Not use this, have some bugs
            out.writeInt(data.length);
            out.writeBytes(data);
        }
    }
}
