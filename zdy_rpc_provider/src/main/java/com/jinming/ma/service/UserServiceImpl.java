package com.jinming.ma.service;

import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService {

    public String sayHello(String word) {
        System.out.println("调用成功--参数 "+word);
        return "调用成功--参数 "+word;
    }



}
