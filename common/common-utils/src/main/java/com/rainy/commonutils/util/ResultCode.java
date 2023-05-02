package com.rainy.commonutils.util;

public interface ResultCode {
    //返回状态成功
    public static Integer SUCCESS = 200;

    //返回创建成功
    public static Integer CREATED = 201;

    //返回用户模块没有登录权限状态码
    public static Integer ERROR = 403;


    // 返回用户模块注册异常状态码
    public static Integer ERROR_USER_REGISTER = 10001;

}
