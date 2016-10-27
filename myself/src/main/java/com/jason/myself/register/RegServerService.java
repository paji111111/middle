package com.jason.myself.register;

import com.jason.myself.transport.RegServiceInfo;

/** 注册中心对外服务
 * Created by liuzhixin on 2016/10/25.
 */
public class RegServerService {

    Register register;

    public RegServerService(Register register) {
        this.register = register;
    }

    public void register(RegServiceInfo regServiceInfo){
        // 注册
        register.regist(regServiceInfo);
    }

}
