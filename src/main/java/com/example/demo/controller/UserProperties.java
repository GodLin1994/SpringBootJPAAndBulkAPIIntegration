package com.example.demo.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.*;

import java.util.List;

public interface UserProperties extends JpaRepository<User,Integer>{
    //通过年龄来查询
    public List<User> findByAge(Integer age);
}
