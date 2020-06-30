package com.hrh.blog.service;

import com.hrh.blog.pojo.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Heerh
 * @version 1.0
 * @date 2020/6/28 23:16
 */
public interface BlogService {
    Blog getBlog(Long id);
    Page<Blog> listBlogs(Pageable pageable,Blog blog);
    Blog saveBlog(Blog blog);
    Blog updateBlog(Long id,Blog blog);
    void deleteBlog(Long id);
}
