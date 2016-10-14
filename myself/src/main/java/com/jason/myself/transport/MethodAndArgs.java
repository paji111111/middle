package com.jason.myself.transport;

import java.io.Serializable;

/**
 * Created by paji on 16/9/7
 */
public class MethodAndArgs implements Serializable {

    private String requestId;
    private String interfaceName;//调用的服务类
    private String methodName;//调用的方法名称
    private Class<?>[] types;//参数类型
    private Object[] args;//参数列表

    public MethodAndArgs() {
        super();
    }

    public MethodAndArgs(String interfaceName,String methodName, Class<?>[] types, Object[] args) {
        this.interfaceName = interfaceName ;
        this.methodName = methodName;
        this.types = types;
        this.args = args;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getTypes() {
        return types;
    }

    public void setTypes(Class<?>[] types) {
        this.types = types;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }




}