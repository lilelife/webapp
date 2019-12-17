package utils;

import lombok.Data;

/**
 *
 */
public abstract  class Response<T> {

    public abstract T getResult();

    public abstract String getMsg();


}
