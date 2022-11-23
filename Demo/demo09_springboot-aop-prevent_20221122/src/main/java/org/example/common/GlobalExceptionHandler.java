package org.example.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * 全局异常捕获
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 入参非法异常捕获
     *
     * @param e
     * @return
     */
    @ExceptionHandler({BusinessException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleServiceException(BusinessException e) {
        log.error("系统异常,{}", e);
        BusinessCode code = new BusinessCode(e.getCode(), e.getMsg());
        return Response.fail(code, e.getMsg());
    }


    /**
     * 系统异常捕获
     *
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleOtherException(Exception e) {
        log.error("系统异常,{}", e);
        return Response.fail(BusinessCode.EXCEPTION, "系统异常");
    }

}