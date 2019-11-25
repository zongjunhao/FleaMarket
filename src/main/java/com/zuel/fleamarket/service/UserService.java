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
     * @param u_stuid
     * @param u_pwd
     * @param u_qq
     * @return
     */
    public BaseResponse register(final String u_stuid, final String u_pwd, final String u_qq) {
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
}
