package com.bxczp.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bxczp.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Resource
    private AccountService accountService;
    @ResponseBody
    @RequestMapping("/tranfer")
    public String tranfer(int fromUser, int toUser, int account) {
        try {
            accountService.transferAccount(fromUser, toUser, account);
            return "OK";
        } catch (Exception e) {
            return "Error";
        }
    }
}
