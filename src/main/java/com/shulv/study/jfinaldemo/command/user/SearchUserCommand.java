package com.shulv.study.jfinaldemo.command.user;

import com.jfinal.aop.Inject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.shulv.study.jfinaldemo.base.BaseCommand;
import com.shulv.study.jfinaldemo.service.UserService;

import java.util.List;

public class SearchUserCommand implements BaseCommand<List<Record>> {
    @Inject
    UserService userService;

    @Override
    public List<Record> execute(){

        List<Record> users = Db.find("select * from user LIMIT 1, 2");
        return users;
    }


}
