package com.zuel.fleamarket.controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.zuel.fleamarket.kit.BaseResponse;
import com.zuel.fleamarket.kit.ResultCodeEnum;
import com.zuel.fleamarket.service.UserService;

public class UserController extends Controller {
    UserService userService = new UserService();
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
}
