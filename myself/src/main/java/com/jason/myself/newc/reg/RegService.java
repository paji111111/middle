package com.jason.myself.newc.reg;

import com.jason.myself.register.AppNode;
import com.jason.myself.register.MethodParamNode;
import com.jason.myself.register.ServiceNode;

import java.sql.SQLException;
import java.util.List;

/** 注册服务
 * Created by liuzhixin on 2016/10/28.
 */
public interface RegService {

    /**
     * 注册服务
     * @param host
     * @param port
     * @param app
     * @param interfaceName
     * @param method_params_list
     * @throws Exception
     */
    public void regist(String host , String port ,String app , String interfaceName, List<String> method_params_list) throws Exception ;

    /**
     * 获取app节点列表
     * @return
     */
    public List<AppNode> pullAppNode() throws SQLException, ClassNotFoundException;

    /**
     * 根据应用名称获取服务列表
     * @param appName
     * @return
     */
    public List<ServiceNode> pullServiceNode(String appName) throws SQLException, ClassNotFoundException;

    /**
     * 根据服务名称获取方法列表
     * @param ServiceName
     * @return
     */
    public List<MethodParamNode> pullMethodParamNode(String ServiceName) throws SQLException, ClassNotFoundException;

}
