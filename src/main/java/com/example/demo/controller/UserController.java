package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserProperties userProperties;

    /**
     * 查询所有user
     * @return
     */
    @GetMapping(value = "/users")
    public List<User> userList(){
        System.out.println(userProperties.findAll().toString());
        return userProperties.findAll();
    }

    /**
     * 添加一个user
     * @param name
     * @param age
     * @return
     */
    @PostMapping(value = "/users")
    public User userAdd(@RequestParam("name") String name,@RequestParam("age") Integer age){
        User user=new User();
        user.setName(name);
        user.setAge(age);
        return userProperties.save(user);
    }
    /**
     * 查询某人
     */
    @GetMapping(value = "/users/{id}")
    public User userFindId(@PathVariable("id") Integer id){
        return userProperties.findOne(id);
    }

    /**
     * 更新某人信息
     */
   @PutMapping(value = "/users/{id}")
   public User userUpdateId(@PathVariable("id") Integer id,@RequestParam("name") String name,@RequestParam("age") Integer age){
       User user=new User();
       user.setId(id);
       user.setName(name);
       user.setAge(age);
       return userProperties.save(user);
   }

    /**
     * 删除某人
     */
    @DeleteMapping(value = "/users/{id}")
    public void userUpdateId(@PathVariable("id") Integer id){
         userProperties.delete(id);
    }

    /**
     * 通过年龄查询人
     */
    @GetMapping(value = "/users/age/{age}")
    public List<User> userByAge(@PathVariable("age") Integer age){
        return userProperties.findByAge(age);

    }
}
