package com.shulv.study.jfinaldemo.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

public abstract class BaseOrganization <M extends BaseOrganization<M>> extends Model<M> implements IBean {
    /**
     * 组织唯一标识
     */
    public void setId(java.lang.String id) {
        set("id", id);
    }

    /**
     * 组织唯一标识
     */
    public java.lang.String getId() {
        return getStr("id");
    }

    /**
     * 组织唯一标识
     */
    public void setName(java.lang.String name) {
        set("name", name);
    }

    /**
     * 组织唯一标识
     */
    public java.lang.String getName() {
        return getStr("name");
    }
}
