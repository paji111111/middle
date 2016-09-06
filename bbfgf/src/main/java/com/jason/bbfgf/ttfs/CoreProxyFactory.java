package com.jason.bbfgf.ttfs;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
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
        // 此处在Callback的子类中之所以选择了MethodInterceptor，而不选择InvocationHandler是因为
        // 1、MethodInterceptor扩展性更好;
        // 2、InvocationHandler与JDK的类重名，书写麻烦.
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
