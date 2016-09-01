package com.jason.bbfgf.client;

/**
 * Created by paji on 16/9/1
 */
public interface IAsyncObjectProxy {
    public KKFuture call(String funcName, Object... args);
}
