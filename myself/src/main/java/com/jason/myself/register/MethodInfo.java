package com.jason.myself.register;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhixin on 2016/10/22.
 */
public class MethodInfo {

    private String name;

    private List<Class>  paramaters;

    public MethodInfo(String name) {
        this.name = name;
        this.paramaters = new ArrayList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Class> getParamaters() {
        return paramaters;
    }

    public void setParamaters(List<Class> paramaters) {
        this.paramaters = paramaters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MethodInfo)) return false;

        MethodInfo that = (MethodInfo) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        return getParamaters() != null ? getParamaters().equals(that.getParamaters()) : that.getParamaters() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getParamaters() != null ? getParamaters().hashCode() : 0);
        return result;
    }
}
