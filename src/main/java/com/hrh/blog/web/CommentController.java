package com.hrh.blog.web;

import com.hrh.blog.pojo.Comment;
import com.hrh.blog.pojo.User;
import com.hrh.blog.service.BlogService;
import com.hrh.blog.service.CommentService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @author Heerh
 * @version 1.0
 * @date 2020/7/2 22:54
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private static final Logger LOG = LoggerFactory.getLogger(CommentController.class);
    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;
    //返回博客的评论片段
    @GetMapping("/comments/{blogId}")
    public String commentList(@PathVariable long blogId, Model model){
        model.addAttribute("comments",commentService.listCommentByBlogId(blogId));
        return "blog :: commentList";
    }
    //点击发布事件
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session) {

        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));
        User user = (User) session.getAttribute("user");
        if (user != null) {
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        } else {
            comment.setAvatar(avatar);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId;
    }
}
