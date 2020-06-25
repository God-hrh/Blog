package com.hrh.blog.dao;

import com.hrh.blog.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Heerh
 * @version 1.0
 * @date 2020/6/25 22:45
 */
public interface UserRepository extends JpaRepository<User,Long>{
    //根据命名规则就能执行相应方法
    User findByUsernameAndPassword(String username,String password);
}
