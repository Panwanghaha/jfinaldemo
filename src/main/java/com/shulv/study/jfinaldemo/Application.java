package com.shulv.study.jfinaldemo;

import com.jfinal.core.JFinal;

public class Application {
    public static void main(String[] args) {
        JFinal.start("src/main/webapp", 8888, "/", 5);
    }
}