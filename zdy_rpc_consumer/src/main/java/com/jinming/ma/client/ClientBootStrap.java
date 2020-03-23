package com.jinming.ma.client;


import com.jinming.ma.service.UserService;

public class ClientBootStrap {

    public static  final String providerName="UserService#sayHello#";

    public static void main(String[] args) throws InterruptedException {

        RpcConsumer rpcConsumer = new RpcConsumer();
        UserService proxy = (UserService) rpcConsumer.createProxy(UserService.class, providerName);

        while (true){
            Thread.sleep(2000);
            String s = proxy.sayHello("are you ok?");

            System.out.println(s);
        }


    }




}
