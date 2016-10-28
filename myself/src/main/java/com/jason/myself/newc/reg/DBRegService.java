package com.jason.myself.newc.reg;


import com.jason.myself.register.DBUtil;
import org.apache.commons.lang3.StringUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by liuzhixin on 2016/10/27.
 */
public class DBRegService implements RegService {

    @Override
    public void regist(String host , String port ,String app , String interfaceName, List<String> method_params_list) throws SQLException, ClassNotFoundException {

        // 查看app是否已存在
        checkAppExist(host,port,app);

        // 检查接口服务端口数据是否存在
        checkInterfaceExist(host,port,app,interfaceName);

        // 检查方法服务是否存在
        checkMethodExist(host,port,app,interfaceName,method_params_list);
    }

    private void checkMethodExist(String host, String port, String app, String interfaceName, List<String> method_params_list) throws SQLException, ClassNotFoundException {
        String nodehead = "/"+app+"/"+interfaceName;
        for (String method_param : method_params_list){
            String node = nodehead+"/"+method_param;
            DBUtils.isNodeExist(node);
            DBUtils.isNodeSigned(node,host,port);
        }
    }

    private void checkInterfaceExist(String host, String port, String app, String interfaceName) throws SQLException, ClassNotFoundException {
        String node ="/"+app+"/"+interfaceName;
        DBUtils.isNodeExist(node);
        DBUtils.isNodeSigned(node,host,port);
    }

    private void checkAppExist(String host , String port  , String app) throws SQLException, ClassNotFoundException {
        DBUtils.isNodeExist(app);
        DBUtils.isNodeSigned(app,host,port);
    }


}
