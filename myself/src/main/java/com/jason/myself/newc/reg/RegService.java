package com.jason.myself.newc.reg;

import java.sql.SQLException;
import java.util.List;

/** 注册服务
 * Created by liuzhixin on 2016/10/28.
 */
public interface RegService {
    public void regist(String host , String port ,String app , String interfaceName, List<String> method_params_list) throws Exception ;
}
