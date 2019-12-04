package com.zuel.fleamarket.controller;

import com.jfinal.core.Controller;

public class IndexController extends Controller {

    public void index() {
        render("/pages/index.html");
    }

    public void test(){
        render("test.html");
    }
}
