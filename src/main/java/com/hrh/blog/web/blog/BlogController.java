package com.hrh.blog.web.blog;

import com.hrh.blog.pojo.Blog;
import com.hrh.blog.pojo.User;
import com.hrh.blog.service.BlogService;
import com.hrh.blog.service.TagService;
import com.hrh.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


/**
 * @author Heerh
 * @version 1.0
 * @date 2020/6/26 0:28
 */
@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    private static final String EDIT = "admin/blogs-edit";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";
    @GetMapping("/blogs")
    public String blogsPage(@PageableDefault(size = 8,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, Blog blog , Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("page",blogService.listBlogs(pageable,blog));
        return LIST;
    }
    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 8,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, Blog blog , Model model){
        model.addAttribute("page",blogService.listBlogs(pageable,blog));
        return "admin/blogs :: bloglist";
    }
    @GetMapping("/blogs/input")
    public String input(Model model){
        //查询到所有标签  初始化标签
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        //new一个空的Blog对象，为了和编辑博客共用页面而设计
        model.addAttribute("blog",new Blog());
        return EDIT;
    }
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        //查询到所有标签  初始化标签
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        //new一个空的Blog对象，为了和编辑博客共用页面而设计
        Blog b = blogService.getBlog(id);
        b.init();
        model.addAttribute("blog",b);
        return EDIT;
    }
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.findType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog b;
        if (blog.getId() == 0) {
            b =  blogService.saveBlog(blog);
        } else {
            b = blogService.updateBlog(blog.getId(), blog);
        }

        if (b == null ) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return REDIRECT_LIST;
    }
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addAttribute("message","删除成功");
        return REDIRECT_LIST;
    }
}
