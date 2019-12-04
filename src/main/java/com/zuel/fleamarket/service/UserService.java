package com.zuel.fleamarket.service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.zuel.fleamarket.kit.BaseResponse;
import com.zuel.fleamarket.kit.ResultCodeEnum;
import com.zuel.fleamarket.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    /**
     * 用户注册
     *
     * @param u_stuid
     * @param u_pwd
     * @param u_qq
     * @return
     */
    public BaseResponse register(final String u_stuid, final String u_pwd, final String u_qq, final String u_name) {
        BaseResponse baseResponse = new BaseResponse();
        List<User> users = User.dao.find("select * from user where u_stuid = " + "'" + u_stuid + "'");
        if (users.size() == 0) {
            // 学号不存在，可以注册
            boolean succeed = Db.tx(new IAtom() {
                @Override
                public boolean run() throws SQLException {
                    User user = new User();
                    user.setUStuid(u_stuid);
                    user.setUPwd(u_pwd);
                    user.setUQq(u_qq);
                    user.setUName(u_name);
                    if (!user.save())
                        return false;
                    return true;
                }
            });

            if (succeed) {
                // 注册成功
                baseResponse.setResult(ResultCodeEnum.REGISTER_SUCCESS);
            } else {
                // 注册失败，数据库操作错误
                baseResponse.setResult(ResultCodeEnum.REGISTER_FAILURE_DB_ERROR);
            }
        } else if (users.size() == 1) {
            // 注册失败，用户已经存在
            baseResponse.setResult(ResultCodeEnum.REGISTER_FAILURE_USER_EXIST);
        } else {
            // 注册失败，未知的系统错误
            baseResponse.setResult(ResultCodeEnum.REGISTER_FAILURE_SYS_ERROR);
        }
        return baseResponse;
    }

    /**
     * 用户登录
     *
     * @param u_stuid
     * @param u_pwd
     * @return
     */
    public BaseResponse login(String u_stuid, String u_pwd) {
        BaseResponse baseResponse = new BaseResponse();
        User user = User.dao.findFirst("select * from user where u_stuid = " + "'" + u_stuid + "'");
        if (user == null) {
            // 用户不存在
            baseResponse.setResult(ResultCodeEnum.NO_EXIST_USER);
        } else {
            if (user.getUPwd().equals(u_pwd)) {
                // 登录成功
                baseResponse.setData(user);
                baseResponse.setResult(ResultCodeEnum.LOGIN_SUCCESS);
            } else {
                // 登录失败，密码错误
                baseResponse.setResult(ResultCodeEnum.LOGIN_ERROR);
            }
        }
        return baseResponse;
    }

    /**
     * 修改用户密码
     *
     * @param u_stuid
     * @param u_old_pwd
     * @param u_new_pwd
     * @return
     */
    public BaseResponse modifyPwd(String u_stuid, String u_old_pwd, String u_new_pwd) {
        BaseResponse baseResponse = new BaseResponse();
        User user = User.dao.findFirst("select * from user where u_stuid =" + "'" + u_stuid + "'");
        if (user != null) {
            // 查找记录不为空
            if (user.getUPwd().equals(u_old_pwd)) {
                user.setUPwd(u_new_pwd);
                if (user.update()) {
                    // 修改密码成功
                    baseResponse.setResult(ResultCodeEnum.MODIFY_SUCCESS);
                } else {
                    // 修改密码失败，数据库错误
                    baseResponse.setResult(ResultCodeEnum.DB_UPDATE_ERROR);
                }
            } else {
                // 原密码错误
                baseResponse.setResult(ResultCodeEnum.MODIFY_FAILURE_PWD_ERROR);
            }
        } else {
            // 该账户不存在
            baseResponse.setResult(ResultCodeEnum.RECORD_NO_EXIST);
        }
        return baseResponse;
    }

    /**
     * 修改用户的信息
     *
     * @param u_stuid
     * @param u_name
     * @param u_gender
     * @param u_phone
     * @param u_qq
     * @return
     */
    public BaseResponse modifyInfo(String u_stuid, String u_name, String u_gender, String u_phone, String u_qq) {
        BaseResponse baseResponse = new BaseResponse();
        User user = User.dao.findFirst("select * from user where u_stuid =" + "'" + u_stuid + "'");
        if (user != null) {
            // 查找记录不为空
            user.setUName(u_name);
            user.setUGender(u_gender);
            user.setUPhone(u_phone);
            user.setUQq(u_qq);
            if (user.update()) {
                // 用户信息修改成功
                baseResponse.setResult(ResultCodeEnum.DB_UPDATE_SUCCESS);
            } else {
                // 用户信息修改失败，数据库错误
                baseResponse.setResult(ResultCodeEnum.DB_UPDATE_ERROR);
            }
        } else {
            // 该账户不存在
            baseResponse.setResult(ResultCodeEnum.RECORD_NO_EXIST);
        }
        return baseResponse;
    }

    /**
     * 查找用户信息
     *
     * @param u_id
     * @return
     */
    public BaseResponse getInfo(String u_id) {
        BaseResponse baseResponse = new BaseResponse();
        User user = User.dao.findFirst("select * from user where u_id =" + "'" + u_id + "'");
        if (user != null) {
            // 查找记录不为空
            baseResponse.setData(user);
            baseResponse.setResult(ResultCodeEnum.USER_QUERY_SUCCESS);
        } else {
            // 该账户不存在
            baseResponse.setResult(ResultCodeEnum.RECORD_NO_EXIST);
        }
        return baseResponse;
    }
}
