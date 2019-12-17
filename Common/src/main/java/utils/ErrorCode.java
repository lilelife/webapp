package utils;

import lombok.Data;

/**
 * 错误码枚举
 */

public enum ErrorCode {
    SUCCESS("200","success"),
    LOGIN_FAILURE("400","登录失败"),
    SERVER_ERROR("500", "系统出错了")
    ;
    private String code;

    private String  msg;



    ErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String   getCode(){
        return code;
    }
    public String   getMsg(){
        return msg;
    }
}
