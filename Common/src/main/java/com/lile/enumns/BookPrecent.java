package com.lile.enumns;

/**
 * 书籍阅读进度
 */
public enum  BookPrecent {
    ZERO(0) //进度0
    ,DONE(100) // 已完成
    ;

    private int precent;

    private BookPrecent(int precent){
        this.precent = precent;
    }
    public int getPrecent() {
        return precent;
    }

    public void setPrecent(int precent) {
        this.precent = precent;
    }
}
