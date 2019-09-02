package com.chun.netty.serializer;

import com.chun.netty.serializer.var.SerializerAlgorithmVar;

/**
 * @Author chun
 * @Date 2019/8/29 10:00
 */
public class SerializerFactory {

    private static JsonSerializer jsonSerializer = new JsonSerializer();

    public static Serializer getSerializer(){
        return getSerializer(SerializerAlgorithmVar.JSON);
    }

    public static Serializer getSerializer(byte serializerAlgorithm){
        switch (serializerAlgorithm){
            case SerializerAlgorithmVar.JSON:
                return jsonSerializer;
        }
        return null;
    }
  }
