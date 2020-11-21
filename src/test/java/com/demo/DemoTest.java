package com.demo;

import java.util.Optional;

import com.demo.cache.CacheableModel;
import com.demo.repository.entity.Data;

public abstract class DemoTest {
	protected static final Long _0L = 0L;
	protected static final Integer NUMBER_ONE = 1;
	protected static final Integer NUMBER_TWO = 2;
	protected static final String API_DATA_URI = "/api/data/";
	protected static final String CLASSNAME_MOCK = "com.demo.Mock";
	protected static final String MESSAGE_MOCK = "Am I a cached message?";

	protected CacheableModel getCacheableMock() {
		return new Data(NUMBER_ONE, MESSAGE_MOCK);
	}

	protected Optional<Data> getDataMock(Integer id) {
		return Optional.of(new Data(id, MESSAGE_MOCK));
	}

	protected Optional<Data> getDataCriteriaZeroMock(Integer id) {
		Data data = new Data(id, MESSAGE_MOCK);
		data.setTimeToRenewCache(_0L);
		return Optional.of(data);
	}
}
