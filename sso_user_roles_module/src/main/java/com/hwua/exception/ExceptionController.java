package com.hwua.exception;

import com.hwua.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @Autowired
    private ResponseData responseData;

    //处理全局异常的方法
    @ExceptionHandler(Exception.class)
    public ResponseData handlerException(Exception e){
        //设置编码为500
        responseData.setCode(500);
        //异常信息
        responseData.setMessage(e.getMessage());
        //设置为空
        responseData.setT(null);
        return responseData;
    }
}
