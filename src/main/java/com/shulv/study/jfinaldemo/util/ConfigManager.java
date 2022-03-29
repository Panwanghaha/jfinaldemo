package com.shulv.study.jfinaldemo.util;

import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;

/**
 * 配置文件管理器
 */
public class ConfigManager {

    /**
     * 配置文件项目集合
     * 默认可不提供配置文件，为防止加载不到配置文件这里默认加载一个空的配置文件
     */
    private static Prop prop = PropKit.use("config.dummy.properties");

    private ConfigManager() {
    }

    static {
        prop.appendIfExists("config.properties");
        String env = getEnv();
        if (env != null && !env.isEmpty()) {
            prop = prop.appendIfExists("config." + getEnv() + ".properties");
        }
    }

    /**
     * 根据当前环境（env）获取所有配置（通用配置和环境相关配置）文件项,加载规则如下：
     * 通用：config.properties,任何环境都需要加载此文件
     * 环境相关:config.[env].properties，根据在config.properties中定义的env=xxx来加载对应环境的配置文件。
     * 以下环境命名为强制约定，其他环境可自行定义：
     * 开发环境：dev
     * 测试环境：beta
     * 正式环境;production
     */
    public static Prop getConfig() {
        return prop;
    }

    /**
     * 获取当前配置文件的适用环境
     */
    private static String getEnv() {
        return prop.get("env");
    }
}