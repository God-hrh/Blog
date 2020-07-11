package com.hrh.blog.service;

import com.hrh.blog.pojo.Comment;

import java.util.List;

/**
 * @author Heerh
 * @version 1.0
 * @date 2020/7/2 23:01
 */
public interface CommentService {
    //获取博客评论列表
    List<Comment> listCommentByBlogId(Long blogId);
    //发布评论时的操作
    Comment saveComment(Comment comment);

}
