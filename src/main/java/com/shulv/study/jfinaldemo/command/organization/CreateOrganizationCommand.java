package com.shulv.study.jfinaldemo.command.organization;

import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleCreateTableStatement;
import com.jfinal.aop.Inject;
import com.shulv.study.jfinaldemo.base.BaseCommand;
import com.shulv.study.jfinaldemo.constant.OrganizationConstant;
import com.shulv.study.jfinaldemo.exception.BusinessException;
import com.shulv.study.jfinaldemo.model.Organization;
import com.shulv.study.jfinaldemo.service.OrganizationService;

import java.util.Map;

public class CreateOrganizationCommand implements BaseCommand<Organization> {
    @Inject
    OrganizationService organizationService;

    @Override
    public Organization execute(){
        Map<String,Object> dateMap = this.extractRequestBody();
        if (dateMap == null || dateMap.isEmpty()){
            throw BusinessException.CUSTOM_ERROR("传入参数不合法");
        }
        String name = (String) dateMap.get(OrganizationConstant.Params.Name);

        return organizationService.create(name);

    }
}
