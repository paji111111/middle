package com.jason.myself.register;

/** 节点类型
 * Created by liuzhixin on 2016/11/14.
 */
public enum NodeType {

    APP(1,"应用"),SERVICE(2,"服务"),METHOD(3,"方法");

    private Integer code;
    private String description;

    NodeType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }


}
