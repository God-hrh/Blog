package com.hrh.blog.web.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Heerh
 * @version 1.0
 * @date 2020/6/26 0:28
 */
@Controller
@RequestMapping("/admin")
public class BlogController {
    @GetMapping("/blogs")
    public String blogsPage(){
        return "admin/blogs";
    }
}
