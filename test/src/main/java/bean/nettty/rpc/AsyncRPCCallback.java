package bean.nettty.rpc;

/**
 * Created by paji on 16/8/30
 */
public interface AsyncRPCCallback {

    void success(Object result);

    void fail(Exception e);

}
