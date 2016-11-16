package com.jason.myself.newc.reg;


import com.jason.myself.register.*;
import org.springframework.beans.BeanUtils;

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
        NodeData queryNodeData = new NodeData();
        queryNodeData.setType(NodeType.APP.getCode());
        List<NodeData> list = queryNode(queryNodeData);
        for (NodeData nodeData : list){
            AppNode appNode = new AppNode();
            BeanUtils.copyProperties(nodeData,appNode);
            appNodeList.add(appNode);
        }

        return appNodeList;
    }

    @Override
    public List<ServiceNode> pullServiceNode(String appName) throws SQLException, ClassNotFoundException {

        List<ServiceNode> serviceNodeList = new ArrayList<>();
        NodeData queryServiceNode = new ServiceNode();
        queryServiceNode.setNodeName(appName);
        queryServiceNode.setType(NodeType.SERVICE.getCode());
        List<NodeData> list = queryNode(queryServiceNode);
        for (NodeData nodeData : list){
            ServiceNode serviceNode = new ServiceNode();
            BeanUtils.copyProperties(nodeData,serviceNode);
            serviceNodeList.add(serviceNode);
        }

        return serviceNodeList;
    }

    @Override
    public List<MethodParamNode> pullMethodParamNode(String ServiceName) throws SQLException, ClassNotFoundException {

        List<MethodParamNode> methodParamNodeList = new ArrayList<>();
        NodeData queryMethodParamNode = new MethodParamNode();
        queryMethodParamNode.setNodeName(ServiceName);
        queryMethodParamNode.setType(NodeType.METHOD.getCode());
        List<NodeData> list = queryNode(queryMethodParamNode);
        for (NodeData nodeData : list){
            MethodParamNode methodParamNode = new MethodParamNode();
            BeanUtils.copyProperties(nodeData,methodParamNode);
            methodParamNodeList.add(methodParamNode);
        }

        return methodParamNodeList;
    }

    private void checkMethodExist(String host, String port, String app, String interfaceName, List<String> method_params_list) throws SQLException, ClassNotFoundException {
        String nodename = "/"+app+"/"+interfaceName;
        for (String method_param : method_params_list){
            String node = nodename+"/"+method_param;
            DBUtils.isNodeSigned(node,NodeType.METHOD,host,port);
        }
    }

    private void checkInterfaceExist(String host, String port, String app, String interfaceName) throws SQLException, ClassNotFoundException {
        String node ="/"+app+"/"+interfaceName;
        DBUtils.isNodeSigned(node,NodeType.SERVICE,host,port);
    }

    private void checkAppExist(String host , String port  , String app) throws SQLException, ClassNotFoundException {
        String nodeName = "/"+app;
        DBUtils.isNodeSigned(nodeName,NodeType.APP,host,port);
    }

    private List<NodeData> queryNode(NodeData nodeData) throws SQLException, ClassNotFoundException {
        return DBUtils.queryNodeDataList(nodeData);
    }

}
