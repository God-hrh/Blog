package com.hrh.blog.service;

import com.hrh.blog.dao.TagRepository;
import com.hrh.blog.exception.NotFoundException;
import com.hrh.blog.pojo.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Heerh
 * @version 1.0
 * @date 2020/6/27 0:05
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository TagRepository;
    @Transactional
    @Override
    public Tag saveTag(Tag Tag) {
        return TagRepository.save(Tag);
    }
    @Transactional
    @Override
    public void deleteTag(Long id) {
        TagRepository.delete(id);
    }
    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = TagRepository.findOne(id);
        if (t==null){
            throw new NotFoundException();
        }
        BeanUtils.copyProperties(tag,t);
        return TagRepository.save(t);
    }
    @Transactional
    @Override
    public Tag findTag(Long id) {
        return TagRepository.findOne(id);
    }

    @Override
    public List<Tag> listTag(String ids) {
        //把"1,2,3"这种字符串的ids转城List
        List<Long> list =  new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        //JPA自带的遍历查询方法
        return TagRepository.findAll(list);
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        //blogs.size从哪来的？
        Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = new PageRequest(0,size,sort);

        return TagRepository.findTop(pageable);
    }

    @Override
    public Tag findTagByName(String name) {
        return TagRepository.findByName(name);
    }

    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return TagRepository.findAll(pageable);
    }

    @Override
    public List<Tag> listTag() {
        return TagRepository.findAll();
    }
}
