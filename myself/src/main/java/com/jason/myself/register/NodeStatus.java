package com.jason.myself.register;

/**
 * Created by liuzhixin on 2016/11/16.
 */
public enum NodeStatus {
    ONLINE(1,"上线"),OFFLINE(0,"下线");

    private Integer code;

    private String description;

    NodeStatus(Integer code, String description) {
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
