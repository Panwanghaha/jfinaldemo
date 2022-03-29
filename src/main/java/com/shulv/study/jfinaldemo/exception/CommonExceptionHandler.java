package com.shulv.study.jfinaldemo.exception;

import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.shulv.study.jfinaldemo.base.BaseExceptionHandler;
import com.shulv.study.jfinaldemo.constant.ResponseConstant;
import com.shulv.study.jfinaldemo.pojo.ResponsePojo;

/**
 * 可对所有Exception及其派生类异常进行处理
 */
public class CommonExceptionHandler extends BaseExceptionHandler {

    public CommonExceptionHandler() {
    }

    /**
     * 将异常及当前上下文环境信息记录到日志中，并将错误消息返回到客户端。
     * 上下文环境信息包括：ip地址，当前登录用户，当前请求路径（包含查询参数）。
     *
     * @param actionInvocation Invocation: com.jfinal.aop
     * @param exception
     */
    public void handle(Invocation actionInvocation, Exception exception) {
        Controller controller = actionInvocation.getController();
        super.log(exception, controller);

        controller.renderJson(
                ResponsePojo.error(
                        ResponseConstant.CodeEnum.FAIL.getValue(),
                        ResponseConstant.CodeEnum.FAIL.getMessage()
                )
        );
    }
}