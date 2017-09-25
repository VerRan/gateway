package com.dt.common.models;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by VerRan.Liu on 2017/9/20.
 */
@Entity
public class ServiceRouter implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serviceCode;//服务编码
    private  String url;//服务对应的url
    private  String sts;//服务路由配置状态 A在用，P注销
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private Date createDate;//创建时间
    private String author;//服务作者
    private String remarks;//服务说明

    public Long getId() {
        return id;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "ServiceRouter{" +
                "id=" + id +
                ", serviceCode='" + serviceCode + '\'' +
                ", url='" + url + '\'' +
                ", sts='" + sts + '\'' +
                ", createDate=" + createDate +
                ", author='" + author + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
