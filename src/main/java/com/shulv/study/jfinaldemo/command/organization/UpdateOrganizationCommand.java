package com.shulv.study.jfinaldemo.command.organization;

import com.jfinal.aop.Inject;
import com.shulv.study.jfinaldemo.base.BaseCommand;
import com.shulv.study.jfinaldemo.constant.OrganizationConstant;
import com.shulv.study.jfinaldemo.exception.BusinessException;
import com.shulv.study.jfinaldemo.model.Organization;
import com.shulv.study.jfinaldemo.service.OrganizationService;

import java.util.Map;

public class UpdateOrganizationCommand implements BaseCommand<Organization> {
    @Inject
    OrganizationService organizationService;

    @Override
    public Organization execute(){
        Map<String,Object> dateMap = this.extractRequestParam();
        if(dateMap == null || dateMap.isEmpty()){
            throw BusinessException.CUSTOM_ERROR("参数不能为空");
        }

        String id = (String) dateMap.get(OrganizationConstant.Params.ID);
        if (id != null){
            String name = (String) dateMap.get(OrganizationConstant.Params.Name);
            Organization organization = new Organization();
            organization.setName(name);
            System.out.println(name);

            organization.update();
            return organization;
        }


        return null;
    }
}
