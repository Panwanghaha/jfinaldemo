package com.shulv.study.jfinaldemo.controller;

import com.jfinal.core.Path;
import com.shulv.study.jfinaldemo.base.BaseController;
import com.shulv.study.jfinaldemo.command.organization.CreateOrganizationCommand;
import com.shulv.study.jfinaldemo.command.organization.FindOrganizationCommand;
import com.shulv.study.jfinaldemo.command.organization.UpdateOrganizationCommand;

@Path("/organization")
public class OrganizationController extends BaseController {
    /**
    * 创建组织
    * Method:GET
    */
    public void create(){
        this.renderJson(exec(CreateOrganizationCommand.class));
    }
    /**
     * 更新组织
     * Method:GET
     */
    public void update(){
        this.renderJson(exec(UpdateOrganizationCommand.class));
    }

    /**
     * 查找指定ID组织信息
     */
    public void find(){
        this.renderJson(exec(FindOrganizationCommand.class));
    }
}
