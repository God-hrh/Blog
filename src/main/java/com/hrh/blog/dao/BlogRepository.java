package com.hrh.blog.dao;

import com.hrh.blog.pojo.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Heerh
 * @version 1.0
 * @date 2020/6/28 23:23
 */
//JpaSpecificationExecutor<Blog>实现了高级查询（条件组合查询）
public interface BlogRepository extends JpaRepository<Blog ,Long> , JpaSpecificationExecutor<Blog> {
}
