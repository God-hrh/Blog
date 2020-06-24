package com.hrh.blog.web;

import com.hrh.blog.exception.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Heerh
 * @version 1.0
 * @date 2020/6/21 12:48
 */
@Controller
public class indexController {
    @RequestMapping("/")
    public String index(@PathVariable String id,@PathVariable String name){
//        String blog = "index1";
//        if (!blog.equals("index")){
//            throw  new NotFoundException("博客不存在");
//        }
        return "index";
    }
    @RequestMapping("/blog")
    public String index(){
//        String blog = "index1";
//        if (!blog.equals("index")){
//            throw  new NotFoundException("博客不存在");
//        }
        return "blog";
    }
}
