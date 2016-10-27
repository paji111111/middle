package com.jason.myself.register;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** 注册中心节点信息
 * Created by liuzhixin on 2016/10/22.
 */
public class RegNodeData {

    private String nodePath; //   /app/package/interface/m_a_b_c_d_e

    private List<AddressInfo> nodeData ;

    public RegNodeData(String nodePath, List<AddressInfo> nodeData) {
        this.nodePath = nodePath;
        this.nodeData = nodeData;
    }

    public String getNodePath() {
        return nodePath;
    }

    public void setNodePath(String nodePath) {
        this.nodePath = nodePath;
    }

    public List<AddressInfo> getNodeData() {
        return nodeData;
    }

    public void setNodeData(List<AddressInfo> nodeData) {
        this.nodeData = nodeData;
    }

    public static void main(String[] args) {
        byte[] bb = new byte[]{ '1','2','3','1'};

        String str = new String(bb);
        System.out.println(str);
    }


    public void tt() throws IOException, KeeperException, InterruptedException, NoSuchAlgorithmException {

//        ZooKeeper zooKeeper = new ZooKeeper("", 5000, new Watcher() {
//            @Override
//            public void process(WatchedEvent watchedEvent) {
//
//            }
//        });
//        List<ACL> aclList = new ArrayList<ACL>();
//
//        Id id = new Id("digest", DigestAuthenticationProvider.generateDigest("admin:admin"));
//        ACL acl = new ACL(ZooDefs.Perms.ALL,id);
//
//        Id id2 = new Id("world","anyone");
//        ACL acl2 = new ACL(ZooDefs.Perms.READ,id2);
//
//        aclList.add(acl);
//        aclList.add(acl2);
//
//        zooKeeper.addAuthInfo("digest","admin:admin".getBytes());
//        zooKeeper.create("/aa/xas/asdas/cas","mm".getBytes("UTF-8") ,aclList , CreateMode.EPHEMERAL);
//
//        Stat stat = null;
//
//        zooKeeper.getData("/app/",true,null );
    }
}
