package com.hrh.blog.dao;

import com.hrh.blog.pojo.Tag;
import com.hrh.blog.pojo.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Heerh
 * @version 1.0
 * @date 2020/6/27 0:06
 */
public interface TagRepository extends JpaRepository<Tag,Long> {
    Tag findByName(String name);
    @Query("select t from  Tag t")
    List<Tag> findTop(Pageable pageable);
}
