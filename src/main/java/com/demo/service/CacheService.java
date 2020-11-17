package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.cache.Cache;
import com.demo.cache.Cacheable;
import com.demo.repository.entity.Data;

@Service
public class CacheService {
	private final Cache cacheModel;
	private final DataService dataService;

	@Autowired
	public CacheService(DataService dataService) {
		this.dataService = dataService;
		this.cacheModel = Cache.getCacheInstance();
	}

	private interface GetCacheableAction {
		Cacheable execute(Integer id);
	}

	public Cacheable getData(Integer id) {
		return this.getCacheable(Data.class.getName(), id, action -> dataService.getData(id));
	}

	private Cacheable getCacheable(String cacheableClassName, Integer id, GetCacheableAction action) {
		Long currentTime = System.currentTimeMillis();
		if (this.isFirstTimeCache(cacheableClassName)
				|| this.isCurrentTimeMinusCacheTimestampGreatherThanCacheTimeCriteria(currentTime,
						this.cacheModel.getCached(cacheableClassName))) {
			this.cacheModel.setTimestamp(currentTime);
			this.cacheModel.setCached(cacheableClassName, action.execute(id));
		}
		return this.cacheModel.getCached(cacheableClassName);
	}

	private Boolean isFirstTimeCache(final String cacheableClassName) {
		return Boolean.FALSE.equals(this.cacheModel.hasCached(cacheableClassName));
	}

	private Boolean isCurrentTimeMinusCacheTimestampGreatherThanCacheTimeCriteria(final Long currentTime,
			Cacheable cacheable) {
		return (currentTime - this.cacheModel.getTimestamp()) > cacheable.getCacheTimeCriteria();
	}
}
