package com.gys.item.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;
import org.springframework.session.web.http.SessionRepositoryFilter;

@Configuration
public class RedisSessionConfig extends RedisHttpSessionConfiguration {

    @Value("${redis.session.timeout}")
    private int sessionTimeOut;

    @Bean
    @Override
    public <S extends Session> SessionRepositoryFilter<? extends Session> springSessionRepositoryFilter(SessionRepository<S> sessionRepository) {
        ((RedisOperationsSessionRepository)sessionRepository).setDefaultMaxInactiveInterval(sessionTimeOut);
        return super.springSessionRepositoryFilter(sessionRepository);
    }


    /**
     * springboot 关闭Spring-session中对CONFIG的操作
     * @return
     */
   /* @Bean
    public static ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;
    }*/
}
