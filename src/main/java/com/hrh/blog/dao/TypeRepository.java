package com.hrh.blog.dao;

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
public interface TypeRepository extends JpaRepository<Type,Long> {
    Type findByName(String name);
    //@Query()自定义查询
    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);
}
