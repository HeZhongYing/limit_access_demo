package com.hezy.service;

import com.hezy.pojo.User;

/**
 * @author hezhongying
 * @create 2024/9/29 10:04
 */
public interface DemoService {
    String test();

    User getUser(Integer id);

    void deleteUserById(Integer id);
}
