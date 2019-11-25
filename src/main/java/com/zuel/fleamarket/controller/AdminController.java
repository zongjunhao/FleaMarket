package com.zuel.fleamarket.controller;

import com.jfinal.core.Controller;
import com.zuel.fleamarket.kit.BaseResponse;
import com.zuel.fleamarket.kit.ResultCodeEnum;
import com.zuel.fleamarket.service.AdminService;

public class AdminController extends Controller {

    public static AdminService adminService = new AdminService();

    public void index(){
        render("adminController");
    }

    /**
     * 验证码图片
     */
    public void pic() {
        renderCaptcha();
    }

    /**
     * 管理员登录
     */
    public void login() {
        BaseResponse baseResponse = new BaseResponse();
        boolean result = validateCaptcha("inputRandomCode");
        if (result){
            String account = getPara("account");
            String password = getPara("password");
            baseResponse = adminService.login(account, password);
            if (baseResponse.getResultCode().equals("4000")) {
                setSessionAttr(account, "ready");
                System.out.println("用户" + account + "的登陆状态是ready");
            } else {
                setSessionAttr(account, "unready");
                System.out.println("用户" + account + "的登陆状态是unready");
            }
        } else {
            System.out.println("验证码错误");
            baseResponse.setResult(ResultCodeEnum.LOGIN_FAILURE_CODE_ERROR);
        }
        renderJson(baseResponse);
    }
}
