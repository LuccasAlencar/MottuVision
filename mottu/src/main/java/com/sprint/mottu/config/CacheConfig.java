package com.sprint.mottu.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching 
public class CacheConfig {

    @Bean
    public Caffeine<Object, Object> caffeineConfig() {
        return Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(1000);
    }

    @Bean
    public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(List.of(
            new CaffeineCache("usuarios", caffeine.build()),
            new CaffeineCache("motos", caffeine.build()),
            new CaffeineCache("registros", caffeine.build()),
            new CaffeineCache("reconhecimentos", caffeine.build()),
            new CaffeineCache("logAlteracoes", caffeine.build()), 
            new CaffeineCache("cargos", caffeine.build()),
            new CaffeineCache("cameras", caffeine.build()) 
        ));
        return manager;
    }
}