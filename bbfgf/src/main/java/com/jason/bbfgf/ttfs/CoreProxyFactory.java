package com.jason.bbfgf.ttfs;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/** 代理工厂类
 * Created by paji on 16/9/6
 */
public class CoreProxyFactory {



    /**
     * 代理生成执行代理
     * @param clazz
     * @param invcationHandler
     * @param <T>
     * @return
     */
    public <T> T proxy(Class<T> clazz, final InvocationHandler invcationHandler) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(new MethodInterceptorAdaptor(invcationHandler));

        return (T) enhancer.create();
    }

    /**
     * MethodInterceptor的适配器，将  InvocationHandler 适配为
     * {@link MethodInterceptor}
     */
    private static class MethodInterceptorAdaptor implements MethodInterceptor {

        private InvocationHandler invocationHandler;

        private MethodInterceptorAdaptor(InvocationHandler invocationHandler) {
            this.invocationHandler = invocationHandler;
        }

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            return invocationHandler.invoke(obj, method, args);
        }

    }


}
