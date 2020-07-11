package com.hrh.blog.dao;


import com.hrh.blog.pojo.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

/**
 * @author Heerh
 * @version 1.0
 * @date 2020/7/2 23:04
 */
public interface CommentRepository extends JpaRepository<Comment,Long> {
    //根据Blog的id查询一组顶级（ParentComment为null）的评论Comment，一个Blog里面有一组Comment
    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);
}
