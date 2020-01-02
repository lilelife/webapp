package com.lile.enumns;

/**
 * 书状态
 * 状态：0 待读；1在读  每用户一本在读；2读完；3删除
 */
public enum  BookStauts {
    PREPARE(0)
    ,READ(1)
    ,DONE(2)
    ,DELETE(3)
    ;


    private int code;
    private BookStauts(int code){
        this.code = code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
