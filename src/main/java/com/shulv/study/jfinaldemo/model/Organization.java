package com.shulv.study.jfinaldemo.model;

import cn.hutool.core.util.IdUtil;
import com.shulv.study.jfinaldemo.model.base.BaseOrganization;

public class Organization extends BaseOrganization<Organization> {
    public static final Organization dao = new Organization().dao();


    public static Organization create(String name) {
        Organization organization = new Organization();
        organization.setId(IdUtil.fastSimpleUUID());
        organization.setName(name);
//        organization.update();

        return organization;
    }
}
