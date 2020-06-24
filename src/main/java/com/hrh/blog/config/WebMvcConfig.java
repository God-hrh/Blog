package com.hrh.blog.config;

import org.springframework.stereotype.Component;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置静态资源映射
 *
 * @author Heerh
 * @version 1.0
 * @date 2020/6/21 12:48
 **/
//@Component
//public class WebMvcConfig implements WebMvcConfigurer {
//    /**
//     * 添加静态资源文件，外部可以直接访问地址
//     *
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//    }
//
//    @Override
//    public MessageCodesResolver getMessageCodesResolver() {
//        return null;
//    }
//}