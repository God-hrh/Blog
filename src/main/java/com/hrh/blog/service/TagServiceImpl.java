package com.hrh.blog.service;

import com.hrh.blog.dao.TagRepository;
import com.hrh.blog.exception.NotFoundException;
import com.hrh.blog.pojo.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Tag updateTag(Long id, Tag Tag) {
        Tag t = TagRepository.findOne(id);
        if (t==null){
            throw new NotFoundException();
        }
        BeanUtils.copyProperties(Tag,t);
        return TagRepository.save(Tag);
    }
    @Transactional
    @Override
    public Tag findTag(Long id) {
        return TagRepository.findOne(id);
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
}
