package com.jason.bbfgf.ttfs;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by paji on 16/9/5
 */
public class CoreClient {

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(16, 16, 600L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(65536));


    /**
     * 通过代理执行远程
     */

    public <T> T create(Class<T> interfaceClass){
        return Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                new Class<?>[] {interfaceClass},
                InvocationHandler h
                );
    }


    /**
     * 处理客户端接受数据包装
     * @param runnable
     */
    public static void submit(Runnable runnable) {
        threadPoolExecutor.submit(runnable);
    }
}
