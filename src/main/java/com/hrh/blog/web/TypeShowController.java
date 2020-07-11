package com.hrh.blog.web;


import com.hrh.blog.pojo.Blog;
import com.hrh.blog.pojo.Type;
import com.hrh.blog.service.BlogService;
import com.hrh.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 4, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        @PathVariable Long id, Model model) {
        List<Type> types = typeService.listTypeTop(10000);
        if (id == -1) {
           id = types.get(0).getId();
        }
        Blog blogQuery = new Blog();
        Type type = new Type();
        type.setId(id);
        blogQuery.setType(type);
        model.addAttribute("types", types);
        model.addAttribute("page", blogService.listBlogs(pageable, blogQuery));
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
