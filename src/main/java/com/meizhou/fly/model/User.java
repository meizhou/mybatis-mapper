package com.meizhou.fly.model;


import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String PASSWORD = "password";

    public static final String SALT = "salt";

    public static final String EMAIL = "email";

    public static final String MOBILE = "mobile";

    public static final String STATUS = "status";

    public static final String IS_MOBILE = "isMobile";

    public static final String IS_QQ = "isQq";

    public static final String IS_WEIXIN = "isWeixin";

    public static final String IS_WEIBO = "isWeibo";

    public static final String REGISTER_MOBILE_TIME = "registerMobileTime";

    public static final String DELETED_AT = "deletedAt";

    public static final String CREATE_TIME = "createTime";

    public static final String UPDATE_TIME = "updateTime";

    public static final String SERIAL_VERSION_UID = "serialVersionUID";

    private Integer id;

    private String name;

    private String password;

    private String salt;

    private String email;

    private Long mobile;

    private Integer status;

    private String isMobile;

    private String isQq;

    private String isWeixin;

    private String isWeibo;

    private Date registerMobileTime;

    private Integer deletedAt;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIsMobile() {
        return isMobile;
    }

    public void setIsMobile(String isMobile) {
        this.isMobile = isMobile == null ? null : isMobile.trim();
    }

    public String getIsQq() {
        return isQq;
    }

    public void setIsQq(String isQq) {
        this.isQq = isQq == null ? null : isQq.trim();
    }

    public String getIsWeixin() {
        return isWeixin;
    }

    public void setIsWeixin(String isWeixin) {
        this.isWeixin = isWeixin == null ? null : isWeixin.trim();
    }

    public String getIsWeibo() {
        return isWeibo;
    }

    public void setIsWeibo(String isWeibo) {
        this.isWeibo = isWeibo == null ? null : isWeibo.trim();
    }

    public Date getRegisterMobileTime() {
        return registerMobileTime;
    }

    public void setRegisterMobileTime(Date registerMobileTime) {
        this.registerMobileTime = registerMobileTime;
    }

    public Integer getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Integer deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}