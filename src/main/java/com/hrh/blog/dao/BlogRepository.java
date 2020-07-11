package com.hrh.blog.dao;

import com.hrh.blog.pojo.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Heerh
 * @version 1.0
 * @date 2020/6/28 23:23
 */
//JpaSpecificationExecutor<Blog>实现了高级查询（条件组合查询）
public interface BlogRepository extends JpaRepository<Blog ,Long> , JpaSpecificationExecutor<Blog> {
    @Query("select b from  Blog b where b.recommend=true ")
    List<Blog> findTop(Pageable pageable);

    //根据字符串查询
    @Query("select b from  Blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findByQuery(String query,Pageable pageable);
    //更新浏览次数
    @Transactional
    @Modifying
    @Query("update Blog b set b.views = b.views+1 where b.id = ?1")
    int updateViews(Long id);
}
