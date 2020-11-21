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

	public CacheableModel getSingleCacheable(String cacheableClassName, Integer id, final CacheableAction action) {
		Long currentTime = System.currentTimeMillis();
		if (this.isSetCachedNeeded(cacheableClassName, id, currentTime)) {
			this.timestamp = currentTime;
			this.setCache(cacheableClassName, action.executeSingleFetch(id));
		}
		return this.getCached(cacheableClassName);
	}

	private boolean isSetCachedNeeded(String cacheableClassName, Integer id, Long currentTime) {
		return this.isFirstTimeCache(cacheableClassName) || this.isCachedNotTheSameAsRequested(cacheableClassName, id)
				|| this.isCurrentTimeMinusCacheTimestampGreatherThanCacheTimeCriteria(currentTime,
						this.getCached(cacheableClassName));
	}

	private boolean isFirstTimeCache(final String cacheableClassName) {
		return Boolean.FALSE.equals(this.hasCached(cacheableClassName));
	}

	private boolean isCachedNotTheSameAsRequested(String cacheableClassName, Integer id) {
		return Boolean.FALSE.equals(this.getCached(cacheableClassName).getId().equals(id));
	}

	private boolean isCurrentTimeMinusCacheTimestampGreatherThanCacheTimeCriteria(final Long currentTime,
			CacheableModel cacheable) {
		return Boolean.TRUE.equals((currentTime - this.timestamp) > cacheable.getCacheTimeCriteria());
	}

	private boolean hasCached(String cacheableClassName) {
		return this.cachedClasses.containsKey(cacheableClassName);
	}

	private void setCache(String className, CacheableModel cacheable) {
		this.cachedClasses.put(className, cacheable);
	}

	private CacheableModel getCached(String className) {
		return this.cachedClasses.get(className);
	}
}
