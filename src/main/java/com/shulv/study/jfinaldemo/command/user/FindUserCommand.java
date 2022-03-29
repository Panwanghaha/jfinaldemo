package com.shulv.study.jfinaldemo.command.user;

import com.jfinal.aop.Inject;
import com.shulv.study.jfinaldemo.base.BaseCommand;
import com.shulv.study.jfinaldemo.constant.UserConstant;
import com.shulv.study.jfinaldemo.exception.BusinessException;
import com.shulv.study.jfinaldemo.model.User;
import com.shulv.study.jfinaldemo.service.UserService;

import java.util.Map;

public class FindUserCommand implements BaseCommand<User> {
    @Inject
    UserService userService;

    @Override
    public User execute() {
        Map<String, Object> dataMap = this.extractRequestParam();
        if (dataMap == null || dataMap.isEmpty()) {
            throw BusinessException.CUSTOM_ERROR("参数不能为空");
        }
        String id = (String) dataMap.get(UserConstant.Params.ID);

        return userService.find(id);
    }
}
