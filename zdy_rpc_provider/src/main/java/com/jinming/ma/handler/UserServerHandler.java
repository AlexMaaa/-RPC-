package com.jinming.ma.handler;

import com.jinming.ma.pojo.RpcRequest;
import com.jinming.ma.util.SpringContextUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;

public class UserServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {


        //根据className找到bean，根据方法名反射调用方法
        RpcRequest request = (RpcRequest) msg;
        String className = request.getClassName();
        String methodName = request.getMethodName();
        Object bean = SpringContextUtil.getBean(className);
        Method method = bean.getClass().getMethod(methodName,request.getParameterTypes());
        Object invoke = method.invoke(bean, request.getParameters());
        ctx.writeAndFlush(invoke);
    }
}
