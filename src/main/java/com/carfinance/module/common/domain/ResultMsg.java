package com.carfinance.module.common.domain;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ResultMsg {
    private static final Logger log = LoggerFactory.getLogger(ResultMsg.class);
    private Long ret;
    private String msg;
    private Map body = new HashMap();

    /**
     * 返回ret为0的信息
     * @param msg
     * @return
     */
    public static ResultMsg success(String msg) {
        return msg(0l, msg);
    }

    /**
     * 返回ret为0的信息
     * @param msg
     * @param body
     * @return
     */
    public static ResultMsg success(String msg, Map body) {
        return msg(0l, msg, body);
    }

    public static ResultMsg success(String msg, Object... params) {
        return msg(0l, msg, params);
    }

    public static ResultMsg msg(Long ret, String msg) {
        ResultMsg rm = new ResultMsg();
        rm.setRet(ret);
        rm.setMsg(msg);
        return rm;
    }

    /**
     * ResultMsg.msg(-1l,"成功","key1","value1","key2","value2");
     * @param ret
     * @param msg
     * @param params 这里参数为key、value可变长数组
     * @return
     */
    public static ResultMsg msg(Long ret, String msg, Object... params) {
        ResultMsg rm = new ResultMsg();
        rm.setRet(ret);
        rm.setMsg(msg);
        Map body = rm.getBody();
        if(params.length % 2 == 0) {
            for (int i = 0; i < params.length; i += 2) {
                Object param = params[i];
                Object paramValue = params[i+1];
                body.put(param, paramValue);
            }
        } else {
            log.error("参数必须为偶数个，符合Key/Value");
        }
        return rm;
    }

    public static ResultMsg msg(Long ret, String msg, Map body) {
        ResultMsg rm = new ResultMsg();
        rm.setRet(ret);
        rm.setMsg(msg);
        rm.setBody(body);
        return rm;
    }

    public Long getRet() {
        return ret;
    }

    public void setRet(Long ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map getBody() {
        return body;
    }

    public void setBody(Map body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ResultMsg{" +
                "ret=" + ret +
                ", msg='" + msg + '\'' +
                ", body=" + body +
                '}';
    }
}
