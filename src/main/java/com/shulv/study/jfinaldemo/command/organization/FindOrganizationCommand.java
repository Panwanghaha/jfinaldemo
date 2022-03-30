package com.shulv.study.jfinaldemo.command.organization;

import com.jfinal.aop.Inject;
import com.shulv.study.jfinaldemo.base.BaseCommand;
import com.shulv.study.jfinaldemo.constant.OrganizationConstant;
import com.shulv.study.jfinaldemo.constant.UserConstant;
import com.shulv.study.jfinaldemo.exception.BusinessException;
import com.shulv.study.jfinaldemo.model.Organization;
import com.shulv.study.jfinaldemo.service.OrganizationService;

import java.util.Map;

public class FindOrganizationCommand implements BaseCommand<Organization> {
    @Inject
    OrganizationService organizationService;

    @Override
    public Organization execute(){
        Map<String, Object> dataMap = this.extractRequestParam();
        if (dataMap == null || dataMap.isEmpty()) {
            throw BusinessException.CUSTOM_ERROR("参数不能为空");
        }
        String id = (String) dataMap.get(OrganizationConstant.Params.ID);
        System.out.println(id);

        return organizationService.find(id);


    }

}
