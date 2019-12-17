package com.lile.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.session.SessionProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableAutoConfiguration
public class RedisConfig {

    private Logger logger =LoggerFactory.getLogger(RedisConfig.class);

    @Value("${spring.redis.hostName}")
    private String host;

    @Value("${spring.redis.password}")
    private String password;

    @Bean
    @ConfigurationProperties(prefix = "spring.redis.pool")
    public JedisPoolConfig getRedisConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        return config;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.redis")
    public JedisConnectionFactory getConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setUsePool(true);
        JedisPoolConfig config = getRedisConfig();
        factory.setPoolConfig(config);
        logger.info("JedisConnectionFactory bean init success.");
        return factory;
    }



    @Bean
    public RedisTemplate<?, ?> getRedisTemplate() {
        JedisConnectionFactory factory = getConnectionFactory();
        logger.info(this.host+","+factory.getHostName()+","+factory.getDatabase());
        logger.info(this.password+","+factory.getPassword());

        factory.setHostName(this.host);
        factory.setPassword(this.password);
        RedisTemplate<?, ?> template = new StringRedisTemplate(getConnectionFactory());
        return template;
    }


}
