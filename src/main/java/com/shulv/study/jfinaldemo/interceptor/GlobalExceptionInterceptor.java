package com.shulv.study.jfinaldemo.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.shulv.study.jfinaldemo.base.BaseExceptionHandler;
import com.shulv.study.jfinaldemo.exception.BusinessException;
import com.shulv.study.jfinaldemo.exception.BusinessExceptionHandler;
import com.shulv.study.jfinaldemo.exception.CommonExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理拦截器
 */
public class GlobalExceptionInterceptor implements Interceptor {
    private static final Map<Class<? extends Exception>, BaseExceptionHandler> exceptionHandlers = new HashMap<>() {{
        put(Exception.class, new CommonExceptionHandler());
        put(BusinessException.class, new BusinessExceptionHandler());
    }};

    @Override
    public void intercept(Invocation invocation) {
        try {
            invocation.invoke();
        } catch (Exception e) {
            BaseExceptionHandler baseExceptionHandler = GlobalExceptionInterceptor.exceptionHandlers.get(e.getClass());
            if (baseExceptionHandler == null) {
                GlobalExceptionInterceptor.exceptionHandlers.get(Exception.class).handle(invocation, e);
            } else {
                baseExceptionHandler.handle(invocation, e);
            }
        }
    }
}