package com.example.demo.controller;

import com.example.demo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserProperties userProperties;

    /**
     * 查询所有user
     *
     * @return
     */
    @GetMapping(value = "/users")
    public List<User> userList() {
        System.out.println(userProperties.findAll().toString());
        return userProperties.findAll();
    }

    /**
     * @param user
     * @return
     */
    @PostMapping(value = "/users")
    public Result<User> userAdd(@Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            /* 采用工具的的形式防止代码重复
            Result result = new Result();
            result.setCode(1);
            result.setMsg(bindingResult.getFieldError().getDefaultMessage());*/
            System.out.println("bindingResult====" + bindingResult.getFieldError().getDefaultMessage());
            return ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
        }

        user.setUserName(user.getUserName());
        user.setName(user.getName());
        user.setAge(user.getAge());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        /* 采用工具的的形式防止代码重复
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(userProperties.save(user));*/
        return ResultUtil.success(userProperties.save(user));
    }

    /**
     * 查询某人
     */
    @GetMapping(value = "/users/{id}")
    public User userFindId(@PathVariable("id") Integer id) {
        return userProperties.findOne(id);
    }

    /**
     * @param id
     * @param username
     * @param name
     * @param age
     * @param email
     * @param password
     * @return
     */
    @PutMapping(value = "/users/{id}")
    public User userUpdateId(@PathVariable("id") Integer id, @RequestParam("userName") String username, @RequestParam("name") String name,
                             @RequestParam("age") Integer age, @RequestParam("email") String email, @RequestParam("password") String password) {
        User user = new User();
        user.setId(id);
        user.setUserName(username);
        user.setName(name);
        user.setAge(age);
        user.setEmail(email);
        user.setPassword(password);
        return userProperties.save(user);
    }

    /**
     * 删除某人
     */
    @DeleteMapping(value = "/users/{id}")
    public void userUpdateId(@PathVariable("id") Integer id) {
        userProperties.delete(id);
    }

    /**
     * 通过年龄查询人
     */
    @GetMapping(value = "/users/age/{age}")
    public List<User> userByAge(@PathVariable("age") Integer age) {
        return userProperties.findByAge(age);

    }
}
