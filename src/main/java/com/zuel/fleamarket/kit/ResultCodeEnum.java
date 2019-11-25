package com.zuel.fleamarket.kit;


public enum ResultCodeEnum
{
	
	SITES_OPEN("1001","网页打开成功"),
	INTERNTE_FAILURE("1002","网络错误，请重试"),
	
	DB_CONNECTION_SUCCESS("200O","数据库连接成功"),
	DB_CONNECTION_FAILURE("2001","数据库连接失败"),
	DB_UPDATE_SUCCESS("2002","数据库修改成功"),
	DB_UPDATE_ERROR("2003","数据库修改失败"),
	DB_ERROR_OVERFLOW("2004","数据库修改失败_字段字数超过规定"),
	DB_ERROR_FORMAT("2005","数据库修改失败_字段输入数据格式错误"),
	DB_FIND_SUCCESS("2006","数据库查找成功"),
	DB_FIND_FAILURE("2007","数据库查找失败，没有该条记录"),
	DB_WORNING_NULL_WRONGPARA("2008","该次查询结果为空_输入参数错误"),
	DB_DELETE_SUCCESS("2009","数据删除成功"),
	DB_DELETE_FAILURE("2010","数据删除失败"),
		
	PARA_FORMAT_ERROR("3000","请求的参数格式错误"),
	PARA_NUM_ERROR("3001","请求的参数个数错误"),
	PARA_PHONENUM_ERROR("3002","错误的手机号"),
	PARA_EMAIL_ERROR("3003","错误的邮箱格式"),
	PARA_PASSWORD_ERROR("3004","错误的密码格式"),
	
	LOGIN_SUCCESS("4000","登录成功"),
	LOGIN_ERROR("4001","登录失败_账号或密码错误"),
	NO_EXIST_USER("4002","登录失败_用户不存在"),
	NO_ENOUGH_MES("4003","登录失败_账号或密码为空"),
	LOGOUT_SUCCESS("4004","退出登录成功"),
	NO_LOGIN_USER("4005","退出登录失败_用户未登录");
	
	private String code;
    private String desc;

    ResultCodeEnum(String code, String desc)
    {
        this.code = code;
        this.desc = desc;
    }

    public String getCode()
    {
        return code;
    }

    public String getDesc()
    {
        return desc;
    }


}
