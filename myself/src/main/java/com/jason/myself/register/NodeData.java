package com.jason.myself.register;

import java.io.Serializable;

/**节点 存储数据
 * Created by liuzhixin on 2016/10/27.
 */
public class NodeData implements Serializable{
    private static final long serialVersionUID = -4435991473297440672L;

    private Integer id;

    private String nodeName;

    private Integer type;

    private String ip;

    private String port;

    private Integer status;


    public Integer getId() {
        return id;
    }

    public NodeData setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getNodeName() {
        return nodeName;
    }

    public NodeData setNodeName(String nodeName) {
        this.nodeName = nodeName;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public NodeData setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public NodeData setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getPort() {
        return port;
    }

    public NodeData setPort(String port) {
        this.port = port;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public NodeData setStatus(Integer status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "NodeData{" +
                "id=" + id +
                ", nodeName='" + nodeName + '\'' +
                ", type=" + type +
                ", ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", status=" + status +
                '}';
    }
}
