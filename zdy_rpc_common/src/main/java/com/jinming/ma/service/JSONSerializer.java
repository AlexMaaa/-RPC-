package com.jinming.ma.service;

import com.alibaba.fastjson.JSON;

import java.io.IOException;

/**
 * @author majm
 * @create 2020-03-23 20:48
 * @desc 采用JSON的方式，定义JSONSerializer的实现类:（其他序列化方式，可以自行实现序列化接口）
 **/
public class JSONSerializer implements Serializer {
    @Override
    public byte[] serialize(Object object) throws IOException {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) throws IOException {
        return JSON.parseObject(bytes, clazz);
    }
}
