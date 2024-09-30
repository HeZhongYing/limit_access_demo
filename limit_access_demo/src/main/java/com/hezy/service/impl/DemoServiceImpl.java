package com.hezy.service.impl;

import com.hezy.annotation.LimitAccess;
import com.hezy.mapper.UserMapper;
import com.hezy.pojo.User;
import com.hezy.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hezhongying
 * @create 2024/9/29 10:04
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private UserMapper userMapper;

    @LimitAccess(key = "test", times = 3, duration = 1)
    @Override
    public String test() {
        return "demo";
    }

    @Override
    public User getUser(Integer id) {
        return userMapper.selectUserById(id);
    }

    @LimitAccess(key = "deleteUserById", times = 3, duration = 1)
    @Transactional
    @Override
    public void deleteUserById(Integer id) {
        // 删除用户
        userMapper.deleteUserById(id);

        int i = 1 / 0;

        // 删除用户对应的角色
        userMapper.deleteUserRoleMapper(id);
    }
}
