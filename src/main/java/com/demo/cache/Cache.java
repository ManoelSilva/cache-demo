package com.demo.cache;

import java.util.HashMap;
import java.util.Map;

public class Cache {
	private static final Cache cacheInstance = new Cache();
	private Map<String, CacheableModel> cachedClasses = new HashMap<>();
	private Long timestamp;

	private Cache() {
		this.timestamp = System.currentTimeMillis();
	}

	public static Cache getCacheInstance() {
		return cacheInstance;
	}

	public CacheableModel getSingleCacheable(String cacheableClassName, Integer id, final CacheableActions action) {
		Long currentTime = System.currentTimeMillis();
		if (this.isFirstTimeCache(cacheableClassName)
				|| this.isCurrentTimeMinusCacheTimestampGreatherThanCacheTimeCriteria(currentTime,
						this.getCached(cacheableClassName))) {
			this.timestamp = currentTime;
			this.setCache(cacheableClassName, action.executeSingleFetch(id));
		}
		return this.getCached(cacheableClassName);
	}

	private Boolean isFirstTimeCache(final String cacheableClassName) {
		return Boolean.FALSE.equals(this.hasCached(cacheableClassName));
	}

	private Boolean isCurrentTimeMinusCacheTimestampGreatherThanCacheTimeCriteria(final Long currentTime,
			CacheableModel cacheable) {
		return (currentTime - this.timestamp) > cacheable.getCacheTimeCriteria();
	}

	private Boolean hasCached(String cacheableClassName) {
		return this.cachedClasses.containsKey(cacheableClassName);
	}

	private void setCache(String className, CacheableModel cacheable) {
		this.cachedClasses.put(className, cacheable);
	}

	private CacheableModel getCached(String className) {
		return this.cachedClasses.get(className);
	}
}
