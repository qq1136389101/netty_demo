package com.chun.netty.packet.response;

import com.chun.netty.packet.Packet;
import lombok.Data;

/**
 * @Author chun
 * @Date 2019/9/2 11:48
 */
@Data
public abstract class CommonResponse extends Packet {

    /**
     * 状态码
     */
    protected Integer code;

    /**
     * 描述
     */
    protected String msg;

    /**
     * 数据
     */
    protected Object data;

    public CommonResponse() {
        this.code = 200;
        this.msg = "";
        this.data = null;
    }

    public CommonResponse(Object data) {
        this.code = 200;
        this.msg = "";
        this.data = data;
    }

    public CommonResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public CommonResponse(Integer code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
