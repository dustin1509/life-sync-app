package com.dustin.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

@Configuration
@EnableCaching
@RequiredArgsConstructor
@ConditionalOnExpression("'${spring.cache.type}' != 'none'")
public class CacheConfiguration extends CachingConfigurerSupport {
    private static final String CACHE_PREFIX = "finance-management";
    private final CacheProperties cacheProperties;
    @Value("${redis.time-to-live-in-day}")
    private Long tTL;

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        CacheProperties.Redis redisProperties = cacheProperties.getRedis();
        redisProperties.setTimeToLive(Duration.of(1, ChronoUnit.DAYS));
        RedisCacheConfiguration config =
                RedisCacheConfiguration.defaultCacheConfig()
                        .computePrefixWith(cacheName -> CACHE_PREFIX + "::" + cacheName + "::")
                        .serializeValuesWith(RedisSerializationContext.SerializationPair
                                .fromSerializer(new GenericJackson2JsonRedisSerializer()));

        if (redisProperties.getTimeToLive() != null) {
            config = config.entryTtl(redisProperties.getTimeToLive());
        } else {
            config = config.entryTtl(Duration.ofDays(tTL));
        }

        if (!redisProperties.isCacheNullValues()) {
            config = config.disableCachingNullValues();
        }

        return config;
    }
}
