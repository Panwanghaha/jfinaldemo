package com.shulv.study.jfinaldemo.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

public abstract class BaseUser<M extends BaseUser<M>> extends Model<M> implements IBean {
    /**
     * 用户唯一标识
     */
    public void setId(java.lang.String id) {
        set("id", id);
    }

    /**
     * 用户唯一标识
     */
    public java.lang.String getId() {
        return getStr("id");
    }

    /**
     * 用户登录名（唯一）
     */
    public void setLoginName(java.lang.String loginName) {
        set("loginName", loginName);
    }

    /**
     * 用户登录名（唯一）
     */
    public java.lang.String getLoginName() {
        return getStr("loginName");
    }

    /**
     * 用户真实姓名
     */
    public void setRealName(java.lang.String realName) {
        set("realName", realName);
    }

    /**
     * 用户真实姓名
     */
    public java.lang.String getRealName() {
        return getStr("realName");
    }

    /**
     * 电子邮箱
     */
    public void setEmail(java.lang.String email) {
        set("email", email);
    }

    /**
     * 电子邮箱
     */
    public java.lang.String getEmail() {
        return getStr("email");
    }

    /**
     * 联系电话
     */
    public void setMobile(java.lang.String mobile) {
        set("mobile", mobile);
    }

    /**
     * 联系电话
     */
    public java.lang.String getMobile() {
        return getStr("mobile");
    }
}

