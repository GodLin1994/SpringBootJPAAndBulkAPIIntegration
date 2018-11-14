package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.bulk.BulkApiHelper;
import com.example.demo.bulk.DB2Account;
import com.example.demo.entity.Account;
import com.example.demo.utils.ResultUtil;
import com.sforce.async.AsyncApiException;
import com.sforce.ws.ConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class Account2SDFC {
    @Autowired
    private AccountProperties accountProperties;

    /**
     * 查询所有account
     *
     * @return
     */
    @GetMapping(value = "/DB2Account")
    public List<Account> userList() throws Exception {
        System.out.println(accountProperties.findAll().toString());
        DB2Account.sendData2SFDC(accountProperties.findAll());
        return accountProperties.findAll();
    }

    /**
     * @param
     * @return
     */
    @GetMapping(value = "/Account2DB")
    public List<Account> accountAdd() throws ConnectionException, InterruptedException, AsyncApiException, IOException {


        List<Account> accountList = BulkApiHelper.SFDC2DB();
        System.out.println("accountList-->" + accountList);
        for (Account account : accountList) {
            account.setId(account.getId());
            account.setName(account.getName());
            account.setPhone(account.getPhone());
            accountProperties.save(account);
        }

        return accountList;
    }
}
