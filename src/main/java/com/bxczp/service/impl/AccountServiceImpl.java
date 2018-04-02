package com.bxczp.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bxczp.Dao.AccountDao;
import com.bxczp.entity.Account;
import com.bxczp.service.AccountService;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;

    @Override
    @Transactional //注意引入的類路勁
    public void transferAccount(int fromUser, int toUser, int account) {
        Account fromAccount = accountDao.getOne(fromUser);
        Account toAccount = accountDao.getOne(toUser);
        fromAccount.setBalance(fromAccount.getBalance()-account);
        toAccount.setBalance(toAccount.getBalance()+account);
        accountDao.save(fromAccount);
        accountDao.save(toAccount);
        
    }

}
