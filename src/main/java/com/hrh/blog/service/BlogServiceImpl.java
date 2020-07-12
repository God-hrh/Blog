package com.hrh.blog.service;

import com.hrh.blog.dao.BlogRepository;
import com.hrh.blog.exception.NotFoundException;
import com.hrh.blog.pojo.Blog;
import com.hrh.blog.pojo.Type;
import com.hrh.blog.util.MarkdownUtils;
import com.hrh.blog.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * @author Heerh
 * @version 1.0
 * @date 2020/6/28 23:22
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Override
    public Blog getBlog(Long id) {
        return blogRepository.findOne(id);
    }
    @Transactional
    @Override
    public Blog getAndreserve(Long id) {
        Blog blog = blogRepository.getOne(id);
        Blog b = new Blog();
        BeanUtils.copyProperties(blog,b);
        b.setContent(MarkdownUtils.markdownToHtml(blog.getContent()));
        blogRepository.updateViews(id);
        return b;
    }

    @Override
    public Page<Blog> listBlogs(Pageable pageable ,Blog blog) {
        //findAll里面两个参数，第一个是new Specification，第二个是pageable
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            //Root是把Blog映射成表字段，可以通过属性拿到
            //CriteriaQuery是一个查询容器
            //CriteriaBuilder里面设置具体的条件，比如相等，like条件等
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                //前端是input输入框
                if (!("".equals(blog.getTitle())) && blog.getTitle() != null){
                    predicates.add(cb.like(root.<String>get("title"),"%"+blog.getTitle()+"%"));
                }
                //前端是select选择框
                if (blog.getType()!=null&&blog.getType().getId()!=0){
                    predicates.add(cb.equal(root.<Type>get("type").get("id"),blog.getType().getId()));
                }
                //前端是多选框，判断是否打勾
                if (blog.isRecommend()){
                    predicates.add(cb.equal(root.<Boolean>get("recommend"),blog.isRecommend()));
                }
                //利用容器开始查询
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                //return就行，Spring JAP自动完成sql的拼接查询
                return null;
            }
        },pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC,"updateTime");
        Pageable pageable = new PageRequest(0,size,sort);
        return blogRepository.findTop(pageable);
    }

    @Override
    public Page<Blog> listBlog(String query, Pageable pageable) {
        return blogRepository.findByQuery(query,pageable);
    }

    @Override
    public Page<Blog> listBlog(Long tagId, Pageable pageable) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Join join = root.join("tags");
                return cb.equal(join.get("id"),tagId);
            }
        },pageable);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogRepository.findGroupYear();
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : years) {
            map.put(year, blogRepository.findByYear(year));
        }
        return map;
    }

    @Override
    public Long countBlog() {
        return blogRepository.count();
    }

    //@Transactional 数据库事务注解
    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        if (blog.getId() == 0) {
            blog.setCteateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        } else {
            blog.setUpdateTime(new Date());
        }
        return blogRepository.save(blog);
    }
    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = blogRepository.findOne(id);
        if (b==null){
            throw new NotFoundException("该博客不存在");
        }
        //把blog的值赋给b  MyBeanUtils.getNullPropertyNames(blog)是自定义函数，在赋值的时候过滤掉值为空的属性
        BeanUtils.copyProperties(blog,b, MyBeanUtils.getNullPropertyNames(blog));
        b.setUpdateTime(new Date());
        return blogRepository.save(b);
    }
    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.delete(id);
    }
}
