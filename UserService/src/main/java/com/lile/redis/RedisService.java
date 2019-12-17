package com.lile.redis;

public interface RedisService {

     String getStr(String str);

     boolean set(final String key, final String value);

     String get(final String key);

     boolean expire(final String key, long expire);
}
