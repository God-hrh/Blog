package com.hrh.blog.service;

import com.hrh.blog.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Heerh
 * @version 1.0
 * @date 2020/6/27 0:03
 */
public interface TypeService {
    Type saveType(Type type);
    void deleteType(Long id);
    Type updateType(Long id,Type type);
    Type findType(Long id);
    Type findTypeByName(String name);
    Page<Type> listType(Pageable pageable);
    List<Type> listType();
}
