package com.shulv.study.jfinaldemo.service;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.shulv.study.jfinaldemo.exception.BusinessException;
import com.shulv.study.jfinaldemo.interceptor.ValidatorInterceptor;
import com.shulv.study.jfinaldemo.model.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Before(ValidatorInterceptor.class)
public class UserService {
    /**
     * 创建用户
     *
     * @param loginName 登录名
     * @param realName  真实姓名
     * @param email     电子邮箱
     * @param mobile    手机号
     */
    @Before(Tx.class)
    public User create(@NotNull(message = "登录名不能为空")
                       @Length(max = 50, message = "登录名长度必须在50字符以内") String loginName,
                       @NotNull(message = "真实姓名不能为空")
                       @Length(max = 20, message = "真实姓名长度必须在20字符以内") String realName,
                       @NotNull(message = "电子邮箱不能为空")
                       @Email(message = "电子邮箱格式不正确") String email,
                       String mobile) {
        User user = User.create(loginName, realName, email, mobile);
        if (!user.save()) {
            throw BusinessException.CUSTOM_ERROR("创建用户失败");
        }

        return user;
    }

    /**
     * 查找指定ID用户信息
     *
     * @param id 用户标识
     */
    @NotNull(message = "指定用户不存在")
    public User find(String id) {

        return User.dao.findById(id);
    }
}
