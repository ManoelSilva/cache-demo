package com.demo.cache;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface CacheableModel {
	public Integer getId();

	@JsonIgnore
	public Long getCacheTimeCriteria();
}
