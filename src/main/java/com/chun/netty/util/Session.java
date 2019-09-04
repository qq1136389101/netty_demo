package com.chun.netty.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author chun
 * @Date 2019/9/4 10:05
 */
@Data
public class Session implements Serializable {

    private static final long serialVersionUID = -1046335078570856353L;

    /**
     * 用户id
     */
    private String id;

    /**
     * 用户名
     */
    private String userName;
}
