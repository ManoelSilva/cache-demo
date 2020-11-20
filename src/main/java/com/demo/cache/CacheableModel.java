package com.demo.cache;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface CacheableModel {
	@JsonIgnore
	public Long getCacheTimeCriteria();
}
