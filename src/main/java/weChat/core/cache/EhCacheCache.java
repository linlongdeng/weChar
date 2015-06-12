package weChat.core.cache;

import java.io.IOException;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.config.CacheConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
public class EhCacheCache extends CachingConfigurerSupport {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	@Bean
	public CacheManager cacheManager() {
		net.sf.ehcache.CacheManager ehcacheCacheManager;
		try {
			ehcacheCacheManager = new net.sf.ehcache.CacheManager(
					new ClassPathResource("ehcache.xml").getInputStream());
			EhCacheCacheManager cacheCacheManager = new EhCacheCacheManager(
					ehcacheCacheManager);
			cacheCacheManager.afterPropertiesSet();
			return cacheCacheManager;
		} catch (CacheException e) {
			logger.error("初始化cache失败", e);
		} catch (IOException e) {
			logger.error("初始化cache失败", e);
		}
		return null;

	}

	@Override
	@Bean
	public KeyGenerator keyGenerator() {
		return new SimpleKeyGenerator();
	}

	@Override
	public CacheResolver cacheResolver() {
		return null;
	}

	@Override
	public CacheErrorHandler errorHandler() {
		return null;
	}

}
