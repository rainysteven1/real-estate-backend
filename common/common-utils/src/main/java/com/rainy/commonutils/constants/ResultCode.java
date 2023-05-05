package com.rainy.commonutils.constants;

public interface ResultCode {
    //返回状态成功
    public static Integer SUCCESS = 200;

    //返回创建成功
    public static Integer CREATED = 201;

    //返回用户模块没有登录权限状态码
    public static Integer ERROR = 403;

    public static Integer SERVER_ERROR = 500;

    // 返回用户模块登录异常状态码
    public static Integer ERROR_USER_LOGIN = 10000;


    // 返回用户模块注册异常状态码
    public static Integer ERROR_USER_REGISTER = 10001;

    // 返回用户模块个人信息异常状态码
    public static Integer ERROR_USER_PROFILE = 10002;

    // 返回房屋模块信息异常状态码
    public static Integer ERROR_HOUSE_PROFILE = 10003;

}
