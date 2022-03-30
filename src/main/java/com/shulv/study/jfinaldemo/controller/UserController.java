package com.shulv.study.jfinaldemo.controller;

import com.jfinal.core.Path;
import com.shulv.study.jfinaldemo.base.BaseController;
import com.shulv.study.jfinaldemo.command.user.*;

@Path("/user")
public class UserController extends BaseController {
    /**
     * 分页查询用户信息
     * Method：GET
     */
    public void search() {
        this.renderJson(exec(SearchUserCommand.class));
    }

    /**
     * 创建用户
     * Method：POST
     */
    public void create() {
        this.renderJson(exec(CreateUserCommand.class));
    }

    /**
     * 查找指定ID用户信息
     * Method：GET
     */
    public void find() {
        this.renderJson(exec(FindUserCommand.class));
    }

    /**
     * 更新指定ID用户信息
     * Method：POST
     */
    public void update() {
        this.renderJson(exec(UpdateUserCommand.class));
    }

    /**
     * 删除指定ID用户信息
     * Method：GET
     */
    public void delete(){
        this.renderJson(exec(DeleteUserCommand.class));
    }
}
