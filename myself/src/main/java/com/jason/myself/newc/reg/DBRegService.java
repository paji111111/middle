package com.jason.myself.newc.reg;


import com.jason.myself.register.*;

import java.sql.SQLException;
import java.util.ArrayList;
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

    @Override
    public List<AppNode> pullAppNode() throws SQLException, ClassNotFoundException {
        List<AppNode> appNodeList = new ArrayList<>();
        List<NodeData> list = queryNode(null);
        for (NodeData nodeData : list){
            appNodeList.add((AppNode)nodeData);
        }

        return appNodeList;
    }

    @Override
    public List<ServiceNode> pullServiceNode(String appName) {
        return null;
    }

    @Override
    public List<MethodParamNode> pullMethodParamNode(String ServiceName) {
        return null;
    }

    private void checkMethodExist(String host, String port, String app, String interfaceName, List<String> method_params_list) throws SQLException, ClassNotFoundException {
        String nodehead = "/"+app+"/"+interfaceName;
        for (String method_param : method_params_list){
            String node = nodehead+"/"+method_param;
            DBUtils.isNodeSigned(node,NodeType.METHOD,host,port);
        }
    }

    private void checkInterfaceExist(String host, String port, String app, String interfaceName) throws SQLException, ClassNotFoundException {
        String node ="/"+app+"/"+interfaceName;
        DBUtils.isNodeSigned(node,NodeType.SERVICE,host,port);
    }

    private void checkAppExist(String host , String port  , String app) throws SQLException, ClassNotFoundException {
        DBUtils.isNodeSigned(app,NodeType.APP,host,port);
    }

    private List<NodeData> queryNode(NodeData nodeData) throws SQLException, ClassNotFoundException {
        return DBUtils.queryNodeDataList(nodeData);
    }

}
