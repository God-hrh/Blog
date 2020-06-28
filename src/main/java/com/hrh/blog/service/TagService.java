package com.hrh.blog.service;

import com.hrh.blog.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Heerh
 * @version 1.0
 * @date 2020/6/27 0:03
 */
public interface TagService {
    Tag saveTag(Tag Tag);
    void deleteTag(Long id);
    Tag updateTag(Long id, Tag Tag);
    Tag findTag(Long id);
    Tag findTagByName(String name);
    Page<Tag> listTag(Pageable pageable);
}
