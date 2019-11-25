package com.zuel.fleamarket.service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.zuel.fleamarket.kit.BaseResponse;
import com.zuel.fleamarket.kit.ResultCodeEnum;
import com.zuel.fleamarket.model.Admin;
import com.zuel.fleamarket.model.Goods;
import com.zuel.fleamarket.model.User;

import java.sql.SQLException;
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
            baseResponse.setResult(ResultCodeEnum.RECORD_NO_EXIST);
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
            baseResponse.setResult(ResultCodeEnum.RECORD_NO_EXIST);
        }
        return baseResponse;
    }

    /**
     * 查看所有用户
     *
     * @return 返回用户信息列表
     */
    public BaseResponse viewAllUser() {
        BaseResponse baseResponse = new BaseResponse();
        List<User> users = User.dao.findAll();
        if (users.isEmpty()) { // 用户列表为空
            baseResponse.setResult(ResultCodeEnum.RECORD_NO_EXIST);
        } else {
            baseResponse.setData(users);
            baseResponse.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
        }
        return baseResponse;
    }

    /**
     * 删除用户（可批量删除）
     *
     * @param u_ids 用户id
     * @return 是否删除成功
     */
    public BaseResponse deleteUsers(final String[] u_ids) {
        final BaseResponse baseResponse = new BaseResponse();
        boolean succeed = Db.tx(new IAtom() {
            @Override
            public boolean run() throws SQLException {
                for (String u_id : u_ids) {
                    User user = User.dao.findById(u_id);
                    if (user == null) { // 没有查找到当前用户，操作失败，返回错误信息并回滚
                        baseResponse.setResult(ResultCodeEnum.RECORD_NO_EXIST);
                        return false;
                    } else {
                        if (!user.delete()) { // 用户删除失败
                            baseResponse.setResult(ResultCodeEnum.DB_SYS_ERROR);
                            return false;
                        }
                    }
                }
                return true;
            }
        });
        if (succeed) {
            baseResponse.setResult(ResultCodeEnum.DB_DELETE_SUCCESS);
        }
        return baseResponse;
    }

    /**
     * 查看所有货物
     *
     * @return 返回货物信息列表
     */
    public BaseResponse viewAllGoods() {
        BaseResponse baseResponse = new BaseResponse();
        List<Goods> goods = Goods.dao.findAll();
        if (goods.isEmpty()) { // 货物列表为空
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        } else {
            baseResponse.setData(goods);
            baseResponse.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
        }
        return baseResponse;
    }

    /**
     * 删除货物（可批量删除）
     *
     * @param g_ids 货物id
     * @return 是否删除成功
     */
    public BaseResponse deleteGoods(final String[] g_ids) {
        final BaseResponse baseResponse = new BaseResponse();
        boolean succeed = Db.tx(new IAtom() {
            @Override
            public boolean run() throws SQLException {
                for (String g_id : g_ids) {
                    Goods goods = Goods.dao.findById(g_id);
                    if (goods == null) { // 没有查找到当前货物，操作失败，返回错误信息并回滚
                        baseResponse.setResult(ResultCodeEnum.RECORD_NO_EXIST);
                        return false;
                    } else {
                        if (!goods.delete()) { // 货物删除失败
                            baseResponse.setResult(ResultCodeEnum.DB_SYS_ERROR);
                            return false;
                        }
                    }
                }
                return true;
            }
        });
        if (succeed) {
            baseResponse.setResult(ResultCodeEnum.DB_DELETE_SUCCESS);
        }
        return baseResponse;
    }
}
