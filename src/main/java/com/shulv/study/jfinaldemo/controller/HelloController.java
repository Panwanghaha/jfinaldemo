package com.shulv.study.jfinaldemo.controller;

import com.jfinal.core.Path;
import com.shulv.study.jfinaldemo.base.BaseController;
import com.shulv.study.jfinaldemo.exception.BusinessException;

@Path("hello")
public class HelloController extends BaseController {
    public void index() {
        renderText("Hello JFinal World.");
    }
    public void test(){
        renderText("Jfinal");
    }

    public void ex() {
        throw BusinessException.DEFAULT_ERROR();
    }
}
