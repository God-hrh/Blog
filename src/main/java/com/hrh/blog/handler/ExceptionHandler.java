package com.hrh.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Heerh
 * @version 1.0
 * @date 2020/6/21 13:07
 * 自定义异常处理信息，把错误信息返回到error页面
 * @ControllerAdvice拦截所有的Controlle注解的错误
 * @org.springframework.web.bind.annotation.ExceptionHandler注解表示该方法是各异做异常处理的
 */
@ControllerAdvice
public class ExceptionHandler {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Exception {
        //在日志系统记录错误信息
        LOG.error("Request URL: {},Exception : {}",request.getRequestURL(),e);
        if (AnnotationUtils.getAnnotation(e.getClass(), ResponseStatus.class)!=null){
            throw e;
        }
        //把错误信息返回到error/error
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url",request.getRequestURL());
        modelAndView.addObject("exception",e);
        modelAndView.setViewName("error/error");
        return modelAndView;
    }
}
