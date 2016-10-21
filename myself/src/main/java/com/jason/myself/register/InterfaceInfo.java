package com.jason.myself.register;

import org.apache.commons.collections4.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** 服务接口信息
 * Created by liuzhixin on 2016/10/21.
 */
public class InterfaceInfo {

    private String interfaceName;

    private List<String> methodList;

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public List<String> getMethodList() {
        return methodList;
    }

    public void setMethodList(List<String> methodList) {
        this.methodList = methodList;
    }

    private boolean verifyRepeatMethod() throws Exception {

        if (CollectionUtils.isNotEmpty(methodList)){
            Set methodSet = new HashSet(methodList);
            return methodSet.size() != methodList.size();
        }

        throw  new Exception("verify method error , methods must no repeat");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InterfaceInfo)) return false;

        InterfaceInfo that = (InterfaceInfo) o;

        if (getInterfaceName() != null ? !getInterfaceName().equals(that.getInterfaceName()) : that.getInterfaceName() != null)
            return false;
        return getMethodList() != null ? getMethodList().equals(that.getMethodList()) : that.getMethodList() == null;

    }

    @Override
    public int hashCode() {
        int result = getInterfaceName() != null ? getInterfaceName().hashCode() : 0;
        result = 31 * result + (getMethodList() != null ? getMethodList().hashCode() : 0);
        return result;
    }

}
