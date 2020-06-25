package com.hrh.blog.service;

import com.hrh.blog.pojo.User;

/**
 * @author Heerh
 * @version 1.0
 * @date 2020/6/25 22:42
 */
public interface UserService {
    User checkUser(String username,String password);
}
