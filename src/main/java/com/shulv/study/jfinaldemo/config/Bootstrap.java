package com.shulv.study.jfinaldemo.config;

import com.jfinal.config.*;
import com.jfinal.json.FastJsonFactory;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import com.shulv.study.jfinaldemo.constant.ConfigConstant;
import com.shulv.study.jfinaldemo.interceptor.GlobalExceptionInterceptor;
import com.shulv.study.jfinaldemo.interceptor.GlobalRenderInterceptor;
import com.shulv.study.jfinaldemo.model._MappingKit;
import com.shulv.study.jfinaldemo.util.ConfigManager;

public class Bootstrap extends JFinalConfig {
    @Override
    public void configConstant(Constants me) {
        // 配置开发模式，true 值为开发模式
        me.setDevMode(ConfigManager.getConfig().getBoolean(ConfigConstant.DEV_MODE, false));
        // 设置 Json 转换工厂实现类，此处设置为阿里巴巴的FastJson
        me.setJsonFactory(new FastJsonFactory());
        // 使用 Cglib 代理
        me.setToCglibProxyFactory();
    }

    @Override
    public void configRoute(Routes me) {
        // 使用路由扫描，参数 "demo." 表示只扫描 demo 包及其子包下的路由
        me.scan(ConfigManager.getConfig().get(ConfigConstant.ROUTE_SCAN_PACKAGE));
    }

    @Override
    public void configEngine(Engine me) {

    }

    @Override
    public void configPlugin(Plugins me) {
        /*连接数据库需要开放此注释*/
        DruidPlugin dp = createDruidPlugin();
        me.add(dp);

        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        _MappingKit.mapping(arp);
        me.add(arp);
    }

    @Override
    public void configInterceptor(Interceptors me) {
        me.add(new GlobalExceptionInterceptor());
        me.add(new GlobalRenderInterceptor());
    }

    @Override
    public void configHandler(Handlers me) {

    }

    public static DruidPlugin createDruidPlugin() {
        String jdbcUrl = ConfigManager.getConfig().get(ConfigConstant.Database.JDBC_URL);
        String userName = ConfigManager.getConfig().get(ConfigConstant.Database.USER_NAME);
        String password = ConfigManager.getConfig().get(ConfigConstant.Database.PASSWORD);
        return new DruidPlugin(jdbcUrl, userName, password);
    }
}
