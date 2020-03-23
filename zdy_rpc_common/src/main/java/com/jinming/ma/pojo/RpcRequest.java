package com.jinming.ma.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author majm
 * @create 2020-03-23 20:17
 * @desc
 **/
public class RpcRequest {
    /**
     * 请求对象的ID
     */

    private String requestId;

    /**
     * 类名
     */

    private String className;

    /**
     * 方法名
     */

    private String methodName;

    /**
     * 参数类型
     */

    private Class<?>[] parameterTypes;

    /**
     * 入参
     */

    private Object[] parameters;

    public static RpcRequest newInstance(String providerName,Object[] args){
        RpcRequest request = new RpcRequest();
        request.setRequestId(UUID.randomUUID().toString());
        request.setClassName(providerName.split("#")[0]);
        request.setMethodName(providerName.split("#")[1]);
        request.setParameters(args);
        List<Class<?>> parameterTypes = new ArrayList<>();
        for (Object arg : args) {
            parameterTypes.add(arg.getClass());
        }
        request.setParameterTypes(parameterTypes.toArray(new Class[parameterTypes.size()]));
        return request;
    }


    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
