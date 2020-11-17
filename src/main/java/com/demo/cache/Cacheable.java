package com.demo.cache;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface Cacheable {
	@JsonIgnore
	public Long getCacheTimeCriteria();
}
