package com.hrh.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Heerh
 * @version 1.0
 * @date 2020/6/21 14:54
 * @ResponseStatus(HttpStatus.NOT_FOUND)定义状态码为NOT_FOUND
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{
    //重写RuntimeException的方法
    public NotFoundException() {

    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
