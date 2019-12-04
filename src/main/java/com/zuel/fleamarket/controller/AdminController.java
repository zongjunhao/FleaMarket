package com.zuel.fleamarket.controller;

import com.jfinal.core.Controller;
import com.zuel.fleamarket.kit.BaseResponse;
import com.zuel.fleamarket.kit.ResultCodeEnum;
import com.zuel.fleamarket.service.AdminService;

public class AdminController extends Controller {

    private static AdminService adminService = new AdminService();

    public void index() {
        renderText("adminController");
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
        if (result) {
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

    /**
     * 管理员退出登录
     */
    public void logout() {
        BaseResponse baseResponse = new BaseResponse();
        String account = getPara("a_account");
        if (getSessionAttr(account) != null && getSessionAttr(account).equals("ready")) {
            removeSessionAttr(account);
            baseResponse.setResult(ResultCodeEnum.LOGOUT_SUCCESS);
        } else {
            baseResponse.setResult(ResultCodeEnum.LOGOUT_FAILURE);
        }
        renderJson(baseResponse);
    }

    /**
     * 管理员修改密码
     */
    public void modifyPassword() {
        BaseResponse baseResponse = new BaseResponse();
        String account = getPara("account");
        String oldPassword = getPara("oldPassword");
        String password = getPara("password");
        baseResponse = adminService.modifyPassword(account, oldPassword, password);
        renderJson(baseResponse);
    }

    /**
     * 查看所有用户
     */
    public void viewAllUser() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse = adminService.viewAllUser();
        renderJson(baseResponse);
    }

    /**
     * 删除用户（可批量删除）
     */
    public void deleteUsers() {
        BaseResponse baseResponse = new BaseResponse();
        String[] u_ids = getParaValues("u_ids");
        baseResponse = adminService.deleteUsers(u_ids);
        renderJson(baseResponse);
    }

    /**
     * 查看所有货物
     */
    public void viewAllGoods() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse = adminService.viewAllGoods();
        renderJson(baseResponse);
    }

    /**
     * 删除货物（可批量删除）
     */
    public void deleteGoods(){
        BaseResponse baseResponse = new BaseResponse();
        String[] g_ids = getParaValues("g_ids");
        baseResponse = adminService.deleteGoods(g_ids);
        renderJson(baseResponse);
    }
}
