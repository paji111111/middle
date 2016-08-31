package com.jason.bbfgf.entity;

/**
 * Created by paji on 16/8/31
 */
public class Response {
    private String requestId;
    private Object result;
    private String error;

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Object getResult() {
        return result;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
