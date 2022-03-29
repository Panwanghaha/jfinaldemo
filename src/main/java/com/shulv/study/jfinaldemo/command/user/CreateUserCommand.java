package com.shulv.study.jfinaldemo.command.user;

import com.jfinal.aop.Inject;
import com.shulv.study.jfinaldemo.base.BaseCommand;
import com.shulv.study.jfinaldemo.constant.UserConstant;
import com.shulv.study.jfinaldemo.exception.BusinessException;
import com.shulv.study.jfinaldemo.model.User;
import com.shulv.study.jfinaldemo.service.UserService;

import java.util.Map;

public class CreateUserCommand implements BaseCommand<User> {
    @Inject
    UserService userService;

    @Override
    public User execute() {
        Map<String, Object> dataMap = this.extractRequestBody();
        if (dataMap == null || dataMap.isEmpty()) {
            throw BusinessException.CUSTOM_ERROR("参数不能为空");
        }
        String loginName = (String) dataMap.get(UserConstant.Params.LOGIN_NAME);
        String realName = (String) dataMap.get(UserConstant.Params.REAL_NAME);
        String email = (String) dataMap.get(UserConstant.Params.EMAIL);
        String mobile = (String) dataMap.get(UserConstant.Params.MOBILE);
        return userService.create(loginName, realName, email, mobile);
    }
}
