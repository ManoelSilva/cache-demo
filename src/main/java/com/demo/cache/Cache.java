package com.demo.cache;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Cache {
	private static final Cache cacheInstance = new Cache();
	private Map<String, Cacheable> cachedClasses = new HashMap<>();

	@Setter
	private Long timestamp;

	private Cache() {
		this.timestamp = System.currentTimeMillis();
	}

	public static Cache getCacheInstance() {
		return cacheInstance;
	}

	public void setCached(String className, Cacheable cacheable) {
		this.cachedClasses.put(className, cacheable);
	}

	public Cacheable getCached(String className) {
		return this.cachedClasses.get(className);
	}

	public Boolean hasCached(String cacheableClassName) {
		return this.cachedClasses.containsKey(cacheableClassName);
	}
}
