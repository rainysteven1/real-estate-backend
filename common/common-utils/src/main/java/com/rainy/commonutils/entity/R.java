package com.rainy.commonutils.entity;

import com.rainy.commonutils.util.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R {

    // 将json格式中的字段定义为类属性
    // Swagger注解

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    //私有化构造器，使其他类只能调用提供的方法
    private R() {

    }

    // 结果返回成功静态方法
    public static R success() {
        R r = new R();
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("success");
        return r;
    }

    // 结果返回失败静态方法
    public static R error() {
        R resultMessage = new R();
        resultMessage.setCode(ResultCode.ERROR);
        resultMessage.setMessage("error");
        return resultMessage;
    }


    // 提供自定义信息的方法，由调用者传入
    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }


}


