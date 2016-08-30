package bean.nettty.rpc;

/**
 * Created by paji on 16/8/30
 */
public interface IAsyncObjectProxy {
    public RPCFuture call(String funcName, Object... args);
}
