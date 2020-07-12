package com.hrh.blog.web;

import com.hrh.blog.pojo.Blog;
import com.hrh.blog.pojo.Tag;
import com.hrh.blog.service.BlogService;
import com.hrh.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heerh
 * @version 1.0
 * @date 2020/7/11 16:45
 */
@Controller
public class TagShowController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;
    @GetMapping("/tags/{id}")
    public String tags(@PathVariable Long id, Model model,
                       @PageableDefault(size = 4,sort = {"updateTime"},direction = Sort.Direction.DESC)Pageable pageable){
        List<Tag> tagList = tagService.listTagTop(10000);
        if (id == -1) {
            id = tagList.get(0).getId();
        }
        Blog blog = new Blog();
        Tag tag = new Tag();
        tag.setId(id);
        List<Tag> tags =new ArrayList<>();
        tags.add(tag);
        blog.setTags(tags);
        Page<Blog> blogList = blogService.listBlog(id,pageable);
        model.addAttribute("tags",tagList);
        model.addAttribute("page",blogList);
        model.addAttribute("activeTagId",id);
        return "tags";
    }
}
