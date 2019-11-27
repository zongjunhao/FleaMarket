package com.zuel.fleamarket.kit;


public enum ResultCodeEnum {

    SITES_OPEN("1001", "网页打开成功"),
    INTERNTE_FAILURE("1002", "网络错误，请重试"),
    UNKOWN_ERROE("1003", "未知的错误"),
    REQUEST_NO_PARAM_ID_ERROR("1004", "页面请求参数错误"),
    DB_SYS_ERROR("1005", "数据库错误"),
    RECORD_NO_EXIST("1006", "记录不存在"),

    DB_CONNECTION_SUCCESS("200O", "数据库连接成功"),
    DB_CONNECTION_FAILURE("2001", "数据库连接失败"),
    DB_UPDATE_SUCCESS("2002", "数据库修改成功"),
    DB_UPDATE_ERROR("2003", "数据库修改失败"),
    DB_ERROR_OVERFLOW("2004", "数据库修改失败_字段字数超过规定"),
    DB_ERROR_FORMAT("2005", "数据库修改失败_字段输入数据格式错误"),
    DB_FIND_SUCCESS("2006", "数据库查找成功"),
    DB_FIND_FAILURE("2007", "数据库查找失败，没有该条记录"),
    DB_WORNING_NULL_WRONGPARA("2008", "该次查询结果为空_输入参数错误"),
    DB_DELETE_SUCCESS("2009", "数据删除成功"),
    DB_DELETE_FAILURE("2010", "数据删除失败"),

    PARA_FORMAT_ERROR("3000", "请求的参数格式错误"),
    PARA_NUM_ERROR("3001", "请求的参数个数错误"),
    PARA_PHONENUM_ERROR("3002", "错误的手机号"),
    PARA_EMAIL_ERROR("3003", "错误的邮箱格式"),
    PARA_PASSWORD_ERROR("3004", "错误的密码格式"),

    LOGIN_SUCCESS("4000", "登录成功"),
    LOGIN_ERROR("4001", "登录失败_账号或密码错误"),
    NO_EXIST_USER("4002", "登录失败_用户不存在"),
    NO_ENOUGH_MES("4003", "登录失败_账号或密码为空"),
    LOGOUT_SUCCESS("4004", "退出登录成功"),
    NO_LOGIN_USER("4005", "退出登录失败_用户未登录"),
    LOGIN_FAILURE_CODE_ERROR("4008", "登陆失败，验证码错误"),
    LOGOUT_FAILURE("4009", "退出登录失败"),
    // 注册
    MODIFY_FAILURE_PWD_ERROR("5000", "修改失败，密码错误"),
    REGISTER_SUCCESS("5001", "注册成功"),
    REGISTER_FAILURE_DB_ERROR("5002", "注册失败_数据库操作错误"),
    REGISTER_FAILURE_USER_EXIST("5003", "注册失败_账户已存在"),
    REGISTER_FAILURE_SYS_ERROR("5004", "注册失败_系统错误"),


    MODIFY_SUCCESS("5005", "修改成功"),

    CATEGORY_QUERY_NULL("6000", "货品类别查询为空"),
    CATEGORY_QUERY_SUCCESS("6001", "货品查询成功"),
    GOODS_QUERY_SUCCESS("6002", "货品查询成功"),
    GOODS_QUERY_NULL("6003", "货品查询为空"),
    GOODS_DELETE_SUCCESS("6004", "货品删除成功"),
    GOODS_NOT_EXIST("6005", "货品不存在"),
    GOODS_DELETE_FAILURE_DB_ERROR("6006", "货品删除失败_数据库错误"),
    GOODS_UPDATE_STATE_SUCCESS("6007", "货品修改状态成功"),
    GOODS_UPDATE_STATE_FAILURE_DB_ERROR("6008", "货品修改状态失败_数据库错误"),
    GOODS_FOLLOW_SUCCESS("6009", "货品添加关注成功"),
    GOODS_FOLLOW_FAILURE_DB_ERROR("6010", "货品添加关注失败_数据库错误"),
    GOODS_FOLLOW_QUERY_SUCCESS("6011", "货品关注查询成功"),
    GOODS_FOLLOW_QUERY_NULL("6012", "货品关注查询为空"),


    TEST("9000", "测试");


    private String code;
    private String desc;

    ResultCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }


}
