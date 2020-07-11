package com.hrh.blog.web;

import com.hrh.blog.exception.NotFoundException;
import com.hrh.blog.pojo.Blog;
import com.hrh.blog.service.BlogService;
import com.hrh.blog.service.TagService;
import com.hrh.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Heerh
 * @version 1.0
 * @date 2020/6/21 12:48
 */
@Controller
public class indexController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;
    @RequestMapping("/")
    public String index1(@PageableDefault(size = 5,sort = {"updateTime"},direction = Sort.Direction.DESC)Pageable pageable,
                         Model model){
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listTypeTop(4));
        model.addAttribute("tags", tagService.listTagTop(6));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(4));

        return "index";
    }
    @PostMapping("/search")
    public String search(@PageableDefault(size = 4, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query, Model model) {
        model.addAttribute("page", blogService.listBlog("%"+query+"%", pageable));
        model.addAttribute("query", query);
        return "search";
    }

    @RequestMapping("/blog/{id}")
    public String index(@PathVariable Long id,Model model){
        model.addAttribute("blog",blogService.getAndreserve(id));
        return "blog";
    }
}
