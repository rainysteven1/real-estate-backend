package com.rainy.commonutils.exception;

import com.rainy.commonutils.entity.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public R error(CustomException e) {
        e.printStackTrace();
        // 动态返回异常状态码和异常信息
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}
