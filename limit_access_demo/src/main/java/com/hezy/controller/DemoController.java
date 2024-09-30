package com.hezy.controller;

import com.hezy.annotation.LimitAccess;
import com.hezy.pojo.User;
import com.hezy.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hezhongying
 * @create 2024/9/29 9:41
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @LimitAccess(key = "test", times = 3, duration = 1)
    @GetMapping
    public String test() {
        return demoService.test();
    }

    @GetMapping("getUser/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        return demoService.getUser(id);
    }

    @GetMapping("deleteUser/{id}")
    public void deleteUser(@PathVariable("id") Integer id) {
        demoService.deleteUserById(id);
    }
}
