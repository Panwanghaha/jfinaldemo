package com.shulv.study.jfinaldemo.interceptor;

import com.alibaba.fastjson.JSON;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.render.JsonRender;
import com.jfinal.render.NullRender;
import com.jfinal.render.Render;
import com.jfinal.render.TextRender;
import com.shulv.study.jfinaldemo.exception.SystemException;
import com.shulv.study.jfinaldemo.pojo.ResponsePojo;

public class GlobalRenderInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation invocation) {
        invocation.invoke();

        try {
            Controller controller = invocation.getController();
            Render render = controller.getRender();
            if (render instanceof NullRender) {
                controller.renderJson(ResponsePojo.success());
            }

            if (render instanceof JsonRender) {
                JsonRender jsonRender = (JsonRender) render;
                controller.renderJson(ResponsePojo.success(JSON.parse(jsonRender.getJsonText())));
            }

            if (render instanceof TextRender) {
                TextRender textRender = (TextRender) render;
                controller.renderJson(ResponsePojo.success(textRender.getText()));
            }
        } catch (Exception e) {
            throw new SystemException("返回数据异常：" + e.getMessage());
        }
    }
}
