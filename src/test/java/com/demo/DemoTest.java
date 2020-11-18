package com.demo;

import com.demo.cache.Cacheable;

public abstract class DemoTest {
	protected static final String MOCK_CLASSNAME = "com.demo.Mock";

	protected Cacheable getCacheableMock() {
		return new Cacheable() {
			@Override
			public Long getCacheTimeCriteria() {
				return null;
			}
		};
	}
}
