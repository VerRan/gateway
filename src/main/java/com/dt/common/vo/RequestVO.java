package com.dt.common.vo;

import java.io.Serializable;

/**
 * Created by VerRan.Liu on 2017/9/20.
 */
public class RequestVO<T> implements Serializable {
    private String token;//服务请求的令牌
    private String version;//请求协议的版本信息预留
    private String serviceCode;//服务编码，用于标识服务的唯一性
    private T input;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public T getInput() {
        return input;
    }

    public void setInput(T input) {
        this.input = input;
    }
}
