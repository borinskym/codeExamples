package config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableCaching
public class CachingConfig {

    public static final String EVENTS_CACHE = "events";

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(EVENTS_CACHE);
    }

    @CacheEvict(allEntries = true, cacheNames = {EVENTS_CACHE})
    @Scheduled(fixedDelayString = "${events-cache.ttl:5000}")
    public void cacheEvict() {
    }
}
