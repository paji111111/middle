package com.jason.bbfgf.ttfs;

/**
 * Created by paji on 16/9/5
 */
public interface CoreClientCallBack {
    public void success(Object result);

    void fail(RuntimeException e);
}
