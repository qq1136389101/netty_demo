package com.chun.netty.serializer;

import com.alibaba.fastjson.JSONObject;
import com.chun.netty.serializer.var.SerializerAlgorithmVar;

/**
 * JSON 序列化算法类
 *
 * @Author chun
 * @Date 2019/8/28 16:22
 */
public class JsonSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithmVar.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSONObject.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSONObject.parseObject(bytes, clazz);
    }
}
