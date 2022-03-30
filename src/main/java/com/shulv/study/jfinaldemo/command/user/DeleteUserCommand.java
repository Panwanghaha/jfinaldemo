package com.shulv.study.jfinaldemo.command.user;

import com.jfinal.aop.Inject;
import com.shulv.study.jfinaldemo.base.BaseCommand;
import com.shulv.study.jfinaldemo.constant.UserConstant;
import com.shulv.study.jfinaldemo.exception.BusinessException;
import com.shulv.study.jfinaldemo.model.User;
import com.shulv.study.jfinaldemo.service.UserService;

import java.util.HashMap;
import java.util.Map;

public class DeleteUserCommand implements BaseCommand<User> {
    @Inject
    UserService userService;

    @Override
    public User execute(){
        Map<String,Object> dateMap = this.extractRequestBody();
        if(dateMap == null || dateMap.isEmpty()){
            throw BusinessException.CUSTOM_ERROR("输入参数不能为空");
        }
        String id = (String) dateMap.get(UserConstant.Params.ID);
        if ( id != null ){
            User user = userService.find(id);
            user.delete();
            user.update();
        }
        return null;
    }
}
