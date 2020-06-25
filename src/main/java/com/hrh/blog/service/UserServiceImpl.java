package com.hrh.blog.service;

import com.hrh.blog.dao.UserRepository;
import com.hrh.blog.pojo.User;
import com.hrh.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;


/**
 * @author Heerh
 * @version 1.0
 * @date 2020/6/25 22:44
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
