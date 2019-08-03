package com.hw.admin.exception;

import com.hw.admin.model.enums.ResponseCodeEnum;
import com.hw.admin.model.web.ApiOut;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BusinessExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiOut exceptionRes(Exception e){
        return new ApiOut.Builder<>()
                .message(ResponseCodeEnum.SYSTEM_ERROR.desc())
                .debugMessage(e.getMessage())
                .code(500)
                .build();
    }
}
