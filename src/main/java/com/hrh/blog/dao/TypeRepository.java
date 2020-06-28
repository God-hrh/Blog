package com.hrh.blog.dao;

import com.hrh.blog.pojo.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Heerh
 * @version 1.0
 * @date 2020/6/27 0:06
 */
public interface TypeRepository extends JpaRepository<Type,Long> {
    Type findByName(String name);
}
