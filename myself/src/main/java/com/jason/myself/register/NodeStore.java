package com.jason.myself.register;

import java.io.Serializable;
import java.util.List;

/** 节点存储
 * Created by liuzhixin on 2016/10/27.
 */
public class NodeStore implements Serializable {

    private static final long serialVersionUID = 7475302474888910333L;

    private String app;
    private String interfacePath;
    private String interfaceName;
    private List<String> method_params;

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getInterfacePath() {
        return interfacePath;
    }

    public void setInterfacePath(String interfacePath) {
        this.interfacePath = interfacePath;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public List<String> getMethod_params() {
        return method_params;
    }

    public void setMethod_params(List<String> method_params) {
        this.method_params = method_params;
    }
}
