package com.zuel.fleamarket.controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.zuel.fleamarket.kit.BaseResponse;
import com.zuel.fleamarket.kit.ResultCodeEnum;
import com.zuel.fleamarket.service.UserService;

public class UserController extends Controller {
    UserService userService = new UserService();

    /**
     * 获取验证码图片
     */
    public void pic() {
        renderCaptcha();
    }

    /**
     * 用户注册
     */
    public void register() {
        BaseResponse baseResponse = new BaseResponse();
        String u_stuid = getPara("u_stuid");
        String u_pwd = getPara("u_pwd");
        String u_qq = getPara("u_qq");
        String u_name = getPara("u_name");
        if (!StrKit.isBlank(u_stuid) && !StrKit.isBlank(u_pwd) && !StrKit.isBlank(u_qq) && !StrKit.isBlank(u_name)) {
            // 注册信息不为空
            baseResponse = userService.register(u_stuid, u_pwd, u_qq, u_name);
        } else {
            // 提交的表单信息不完整
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        }
        renderJson(baseResponse);
    }

    /**
     * 用户登录
     */
    public void login() {
        BaseResponse baseResponse = new BaseResponse();
        boolean result = validateCaptcha("inputRandomCode");
        if (result) {
            String u_stuid = getPara("u_stuid");
            String u_pwd = getPara("u_pwd");
            if (!StrKit.isBlank(u_stuid) && !StrKit.isBlank(u_pwd)) {
                baseResponse = userService.login(u_stuid, u_pwd);
            } else {
                // 请求的参数不足
                baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
            }
            System.out.println(baseResponse.getResultCode());
            if (baseResponse.getResultCode().equals("4000"))
                setSessionAttr(u_stuid, "ready");
            else
                setSessionAttr(u_stuid, "unready");
        } else {
            // 验证码填写错误
            baseResponse.setResult(ResultCodeEnum.LOGIN_FAILURE_CODE_ERROR);
        }
        renderJson(baseResponse);
    }

    /**
     * 用户退出登录
     */
    public void logout() {
        BaseResponse baseResponse = new BaseResponse();
        String u_stuid = getPara("u_stuid");
        if (getSessionAttr(u_stuid) != null && getSessionAttr(u_stuid).equals("ready")) {
            removeSessionAttr(u_stuid);
            baseResponse.setResult(ResultCodeEnum.LOGOUT_SUCCESS);
        } else {
            baseResponse.setResult(ResultCodeEnum.LOGOUT_FAILURE);
        }
        renderJson(baseResponse);
    }

    /**
     * 修改用户密码
     */
    public void modifyPwd() {
        BaseResponse baseResponse = new BaseResponse();
        String u_stuid = getPara("u_stuid");
        String u_old_pwd = getPara("u_old_pwd");
        String u_new_pwd = getPara("u_new_pwd");
        if (!StrKit.isBlank(u_stuid) && !StrKit.isBlank(u_old_pwd) && !StrKit.isBlank(u_new_pwd)) {
            // 请求的参数不为空
            baseResponse = userService.modifyPwd(u_stuid, u_old_pwd, u_new_pwd);
        } else {
            // 请求的参数个数不足
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        }
        renderJson(baseResponse);
    }

    /**
     * 修改用户的信息
     */
    public void modifyInfo() {
        BaseResponse baseResponse = new BaseResponse();
        String u_stuid = getPara("u_stuid");
        String u_name = getPara("u_name");
        String u_gender = getPara("u_gender");
        String u_phone = getPara("u_phone");
        String u_qq = getPara("u_qq");

        if (!StrKit.isBlank(u_stuid) && !StrKit.isBlank(u_name) && !StrKit.isBlank(u_qq)) {
            baseResponse = userService.modifyInfo(u_stuid, u_name, u_gender, u_phone, u_qq);
        } else {
            // 请求学号、名字、qq不能为空
            baseResponse.setResult(ResultCodeEnum.PARA_NUM_ERROR);
        }
        renderJson(baseResponse);
    }

}
