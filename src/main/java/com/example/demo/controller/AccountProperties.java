package com.example.demo.controller;


import com.example.demo.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountProperties extends JpaRepository<Account,String> {
    //通过名字来查询
   // public List<Account> findByEname(String name);
}
