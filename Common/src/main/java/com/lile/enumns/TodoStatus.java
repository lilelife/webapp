package com.lile.enumns;

/**
 * 代办事项状态
 * 事项状态：0 代办；1完成；2取消
 */
public enum  TodoStatus {
    WAIT(0)
    ,DONE(1)
    ,CANCEL(2)
    ;

    private int code;

    private TodoStatus(int code){
        this.code = code;

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
