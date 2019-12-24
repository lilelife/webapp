package utils;

import exceptions.UncheckedException;
import lombok.Data;

import java.util.function.Supplier;

/**
 * 错误码枚举
 */

public enum ErrorCode {
    SUCCESS("200","success"),
    LOGIN_FAILURE("400","登录失败"),
    NOT_USER("300","用户不存在"),
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

    public Supplier<UncheckedException> throwSupplier(String errMsg) {
        return () -> new UncheckedException(this, errMsg);
    }
    public UncheckedException throwThis(String errMsg) {
        return new UncheckedException(this, errMsg);
    }
}
