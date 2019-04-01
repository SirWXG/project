package com.springboot.boot.utils;

/**
 * layui 封装对象的类
 */
public class SimpleResult {

    private Integer code;
    private String msg;
    private Integer count;
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static SimpleResult getSimple(Integer code, String msg, Integer count, Object data){
        SimpleResult result = new SimpleResult();
        result.setCode(code);
        result.setData(data);
        result.setCount(count);
        result.setMsg(msg);
        return result;
    }

    public static SimpleResult getSimple(Integer code, String msg){
        SimpleResult result = new SimpleResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
