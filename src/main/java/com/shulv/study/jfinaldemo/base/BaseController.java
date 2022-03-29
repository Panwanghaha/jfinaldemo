package com.shulv.study.jfinaldemo.base;

import com.jfinal.aop.Aop;
import com.jfinal.core.Controller;

/**
 * 控制器基类
 */
public class BaseController extends Controller {
    /**
     * 执行指定命令
     *
     * @param commandClass 命令类
     * @param <T>          命令返回指定类型的数据
     */
    protected <T> T exec(Class<? extends BaseCommand<T>> commandClass) {
        BaseCommand.set(this);
        try {
            BaseCommand<T> baseCommand = Aop.get(commandClass);
            return baseCommand.execute();
        } finally {
            BaseCommand.invalidate();
        }
    }
}