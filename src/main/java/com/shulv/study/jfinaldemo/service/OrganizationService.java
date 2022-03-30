package com.shulv.study.jfinaldemo.service;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.shulv.study.jfinaldemo.exception.BusinessException;
import com.shulv.study.jfinaldemo.interceptor.ValidatorInterceptor;
import com.shulv.study.jfinaldemo.model.Organization;
import com.shulv.study.jfinaldemo.model.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Before(ValidatorInterceptor.class)
public class OrganizationService {
    /**
     * 创建组织
     *
     * @param name 组织名称
     * @return organization 返回组织
     */
    @Before(Tx.class)
    public Organization create(String name) {
        Organization organization = Organization.create(name);
        System.out.println(organization);

        if (!organization.save()) {
            throw BusinessException.CUSTOM_ERROR("创建组织失败");
        }

        return organization;
    }

    /**
     * 查找指定ID组织信息
     *
     * @param id 组织标识
     */
    @NotNull(message = "指定组织不存在")
    public Organization find(String id) {
        return Organization.dao.findById(id);
    }
}
