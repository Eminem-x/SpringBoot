package com.yuanhao.admin.service;

import com.yuanhao.admin.bean.Account;
import com.yuanhao.admin.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Yuanhao
 */
@Service
public class AccountService {
    @Autowired
    AccountMapper accountMapper;

    public Account getAccountByName(String name) {
        return accountMapper.getAccount(name);
    }
}
