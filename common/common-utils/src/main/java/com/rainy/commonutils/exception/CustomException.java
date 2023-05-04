package com.rainy.commonutils.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// 生成有参构造器
@AllArgsConstructor
// 生成无参数构造器
@NoArgsConstructor
public class CustomException extends RuntimeException {
    // 定义状态码
    private Integer code;
    // 定义异常信息
    private String msg;
}
