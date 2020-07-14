package com.hrh.blog.service;

import com.hrh.blog.dao.TypeRepository;
import com.hrh.blog.exception.NotFoundException;
import com.hrh.blog.pojo.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Heerh
 * @version 1.0
 * @date 2020/6/27 0:05
 */
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeRepository typeRepository;
    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }
    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.delete(id);
    }
    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeRepository.findOne(id);
        if (t==null){
            throw new NotFoundException();
        }
        BeanUtils.copyProperties(type,t);
        return typeRepository.save(t);
    }
    @Transactional
    @Override
    public Type findType(Long id) {
        return typeRepository.findOne(id);
    }

    @Override
    public Type findTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        //先创建排序
        Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");
        //创建分页容器
        Pageable pageable = new PageRequest(0,size,sort);
        //查询出来
        return typeRepository.findTop(pageable);
    }
}
