package com.soldier.start.model;

import java.io.Serializable;

/**
 * Json信息响应数据类，
 *
 * 泛型返回对象，方便扩展
 *
 */
public class JsonData<T> implements Serializable {

    private static final long serialVersionUID = 5139575728695542003L;

    private int               code             = 200;
    /**  */
    private T                 data;                                   //返回数据

    private String            msg;                                    //提示信息。

    public JsonData() {

    }

    public static JsonData noContent() {
        return new JsonData();
    }

    /**
     * 默认成功 构造返回Json对象
     * @param data
     * @return
     */
    public static JsonData ok(Object data) {
        return JsonData.ok(data,"");
    }

    public static JsonData ok(Object data, String msg) {
        JsonData tJsonData = new JsonData();
        tJsonData.setCode(200);
        tJsonData.setData(data);
        tJsonData.setMsg(msg);
        return tJsonData;
    }

    public static JsonData error(Object data) {
        return JsonData.error(data,"");
    }

    public static JsonData error(Object data, String msg) {
        JsonData tJsonData = new JsonData();
        tJsonData.setCode(1);
        tJsonData.setData(data);
        tJsonData.setMsg(msg);
        return tJsonData;
    }
    public JsonData<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

}
