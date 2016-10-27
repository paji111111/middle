package com.jason.myself.transport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 服务端向注册中心 提交的注册数据
 * Created by liuzhixin on 2016/10/25.
 */
public class RegServiceInfo {

    private String host;

    private String port;

    private String app;

    private String interfaceName;

    private Map<String,String> methodMap = new HashMap<>();// methodName params 可为空



    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public Map<String, String> getMethodMap() {
        return methodMap;
    }

    public void setMethodMap(Map<String, String> methodMap) {
        this.methodMap = methodMap;
    }
}
