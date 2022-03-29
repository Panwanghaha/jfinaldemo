package com.shulv.study.jfinaldemo.base;

import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 所有异常处理的基类
 */
public abstract class BaseExceptionHandler {

    private final Log log = Log.getLog(this.getClass());

    public BaseExceptionHandler() {

    }

    /**
     * @param actionInvocation Invocation: com.jfinal.aop
     * @param exception        异常信息
     */
    public abstract void handle(Invocation actionInvocation, Exception exception);

    protected void log(Exception exception, Controller controller) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("异常", exception.getMessage());
        errorMap.put("堆栈信息", getStackTrace(exception));
        errorMap.put("请求路径", controller.getRequest().getRequestURI());
        errorMap.put("请求参数", controller.getRequest().getQueryString());
        log.error(errorMap.toString());
    }

    private String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            t.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }
}