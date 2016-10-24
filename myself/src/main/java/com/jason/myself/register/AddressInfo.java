package com.jason.myself.register;

/** 地址信息
 * Created by liuzhixin on 2016/10/24.
 */
public class AddressInfo {
    private String host;
    private String port;

    public AddressInfo(String host, String port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }
}
