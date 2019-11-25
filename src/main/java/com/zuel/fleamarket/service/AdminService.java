package com.zuel.fleamarket.service;

import com.zuel.fleamarket.kit.BaseResponse;
import com.zuel.fleamarket.kit.ResultCodeEnum;
import com.zuel.fleamarket.model.Admin;

import java.util.List;

public class AdminService {

    /**
     * 管理员登录
     *
     * @param account  账号
     * @param password 密码
     * @return 成功返回用户信息，失败返回登陆失败信息
     */
    public BaseResponse login(String account, String password) {
        BaseResponse baseResponse = new BaseResponse();
        boolean exist = false;
        // 查找所有管理员账号
        Admin admin = Admin.dao.findFirst("select * from user where a_account = ?", account);
        System.out.println(admin);
        if (admin == null) {
            baseResponse.setResult(ResultCodeEnum.NO_EXIST_USER);
        } else {
            if (admin.getAPwd().equals(password)) {
                baseResponse.setData(admin);
                baseResponse.setResult(ResultCodeEnum.LOGIN_SUCCESS);
            } else {
                baseResponse.setResult(ResultCodeEnum.LOGIN_ERROR);
            }
        }
        return baseResponse;
    }


}
