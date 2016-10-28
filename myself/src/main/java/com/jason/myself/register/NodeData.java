package com.jason.myself.register;

import java.io.Serializable;
import java.util.List;

/**节点 存储数据
 * Created by liuzhixin on 2016/10/27.
 */
public class NodeData implements Serializable{
    private static final long serialVersionUID = -4435991473297440672L;

    private List<String> pathList;

    public List<String> getPathList() {
        return pathList;
    }

    public void setPathList(List<String> pathList) {
        this.pathList = pathList;
    }
}
