package com.yuanhao.admin.controller;

import com.yuanhao.admin.bean.Account;
import com.yuanhao.admin.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yuanhao
 */
@RestController
public class MybatisController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AccountService accountService;

    @GetMapping("/account")
    public Account getByName(@RequestParam("name") String name) {
        return accountService.getAccountByName(name);
    }
}
