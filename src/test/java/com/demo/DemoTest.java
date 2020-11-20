package com.demo;

import java.util.Optional;

import com.demo.cache.CacheableModel;
import com.demo.repository.entity.Data;

public abstract class DemoTest {
	protected static final Integer ID_MOCK = 1;
	protected static final String API_DATA_URI = "/api/data/";
	protected static final String CLASSNAME_MOCK = "com.demo.Mock";
	protected static final String MESSAGE_MOCK = "Am I a cached message?";

	protected CacheableModel getCacheableMock() {
		return new Data(ID_MOCK, MESSAGE_MOCK);
	}

	protected Optional<Data> getDataMock() {
		return Optional.of(new Data());
	}
}
