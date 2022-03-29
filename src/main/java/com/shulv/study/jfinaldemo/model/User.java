package com.shulv.study.jfinaldemo.model;

import cn.hutool.core.util.IdUtil;
import com.shulv.study.jfinaldemo.model.base.BaseUser;

public class User extends BaseUser<User> {
    public static final User dao = new User().dao();

    public static User create(String loginName, String realName, String email, String mobile) {
        User user = new User();
        user.setId(IdUtil.fastSimpleUUID());
        user.setLoginName(loginName);
        user.setRealName(realName);
        user.setEmail(email);
        user.setMobile(mobile);
        return user;
    }
}

