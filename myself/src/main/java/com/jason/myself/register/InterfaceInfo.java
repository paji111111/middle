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

    private List<MethodInfo> methodInfoList;

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public List<MethodInfo> getMethodInfoList() {
        return methodInfoList;
    }

    public void setMethodInfoList(List<MethodInfo> methodInfoList) {
        this.methodInfoList = methodInfoList;
    }

    private boolean verifyRepeatMethod() throws Exception {
        if (CollectionUtils.isNotEmpty(methodInfoList)){
            Set methodSet = new HashSet(methodInfoList);
            return methodSet.size() == methodInfoList.size();
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
        return getMethodInfoList() != null ? getMethodInfoList().equals(that.getMethodInfoList()) : that.getMethodInfoList() == null;
    }

    @Override
    public int hashCode() {
        int result = getInterfaceName() != null ? getInterfaceName().hashCode() : 0;
        result = 31 * result + (getMethodInfoList() != null ? getMethodInfoList().hashCode() : 0);
        return result;
    }

}
