package com.jason.myself.register;

import io.netty.util.internal.StringUtil;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/** 服务配置信息
 * Created by liuzhixin on 2016/10/21.
 */
public class ServiceConfig {

    private String host;

    private String port;

    private List<InterfaceInfo> interfaceInfoList ;

    public ServiceConfig(String host, String port) {
        this.host = host;
        this.port = port;
        interfaceInfoList = new ArrayList<InterfaceInfo>();
    }

    public void addInterfaceInfo(InterfaceInfo interfaceInfo){
        if (interfaceInfo != null && !StringUtils.isEmpty(interfaceInfo.getInterfaceName())){
            interfaceInfoList.add(interfaceInfo);
        }
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public List<InterfaceInfo> getInterfaceInfoList() {
        return interfaceInfoList;
    }
}
