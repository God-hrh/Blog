package com.hrh.blog.service;

import com.hrh.blog.pojo.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @author Heerh
 * @version 1.0
 * @date 2020/6/28 23:16
 */
public interface BlogService {
    Blog getBlog(Long id);
    //查询出Blog并把博客内容转义
    Blog getAndreserve(Long id);

    //根据条件查询出来的分页方法
    Page<Blog> listBlogs(Pageable pageable,Blog blog);
    //无条件查询的分页方法
    Page<Blog> listBlog(Pageable pageable);
    //取list的前几条数据
    List<Blog> listRecommendBlogTop(Integer size);
    //根据字符串查询的方法
    Page<Blog> listBlog(String query,Pageable pageable);
    //根据tagId查询Blog
    Page<Blog> listBlog(Long tagId,Pageable pageable);
    //归档
    Map<String,List<Blog>> archiveBlog();
    //查询blog总数
    Long countBlog();
    Blog saveBlog(Blog blog);
    Blog updateBlog(Long id,Blog blog);
    void deleteBlog(Long id);
}
