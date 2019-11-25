package com.zuel.fleamarket.service;

import com.zuel.fleamarket.kit.BaseResponse;
import com.zuel.fleamarket.kit.ResultCodeEnum;
import com.zuel.fleamarket.model.Admin;
import com.zuel.fleamarket.model.User;

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
        // 查找所有管理员账号
        Admin admin = Admin.dao.findFirst("select * from admin where a_account = ?", account);
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

    /**
     * 管理员修改密码
     *
     * @param account     管理员账号
     * @param oldPassword 旧密码
     * @param password    新密码
     * @return 返回是否修改成功
     */
    public BaseResponse modifyPassword(String account, String oldPassword, String password) {
        BaseResponse baseResponse = new BaseResponse();
        Admin admin = Admin.dao.findFirst("select * from admin where a_account = ?", account);
        if (admin != null) { // 查找成功
            if (admin.getAPwd().equals(oldPassword)) { // 原密码是否正确
                admin.setAPwd(password);
                if (admin.update()) { // 更新成功
                    baseResponse.setData(admin);
                    baseResponse.setResult(ResultCodeEnum.MODIFY_SUCCESS);
                } else { // 更新失败
                    baseResponse.setResult(ResultCodeEnum.DB_UPDATE_ERROR);
                }
            } else { // 原密码错误
                baseResponse.setResult(ResultCodeEnum.MODIFY_FAILURE_PWD_ERROR);
            }
        } else {
            baseResponse.setResult(ResultCodeEnum.DB_UPDATE_ERROR);
        }
        return baseResponse;
    }

    public BaseResponse viewAllUser() {
        BaseResponse baseResponse = new BaseResponse();
        List<User> users = User.dao.findAll();
        if (users.isEmpty()) {
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        } else {
            baseResponse.setData(users);
            baseResponse.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
        }
        return baseResponse;
    }
}
