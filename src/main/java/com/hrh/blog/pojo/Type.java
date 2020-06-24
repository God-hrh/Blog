package com.hrh.blog.pojo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Heerh
 * @version 1.0
 * @date 2020/6/22 23:09
 * 博客分类
 * @OneToMany(mappedBy = "type")和博客类里的关系正好相反 ,参数表示被维护的表在维护表里的属性
 */
@Entity
@Table(name = "t_type")
public class Type {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<>();
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public Type() {
    }

    public Type(String name, List<Blog> blogs) {
        this.name = name;
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
