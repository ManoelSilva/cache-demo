package com.demo.cache;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.demo.DemoTest;

public class CacheTest extends DemoTest {
	@InjectMocks
	private Cache cache;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.cache = Mockito.spy(this.cache);
	}

	@Test
	public void getTimestampTest() {
		assertThat(this.cache.getTimestamp(), instanceOf(Long.class));
	}

	@Test
	public void setTimestampTest() {
		Long currentTime = System.currentTimeMillis();
		assertNotEquals(currentTime, this.cache.getTimestamp());
		this.cache.setTimestamp(currentTime);
		assertEquals(currentTime, this.cache.getTimestamp());
	}

	@Test
	public void getCacheInstanceTest() {
		assertThat(Cache.getCacheInstance(), instanceOf(Cache.class));
	}

	@Test
	public void setCachedTest() {
		Cacheable cacheableMock = this.getCacheableMock();
		assertNotEquals(cacheableMock, this.cache.getCached(MOCK_CLASSNAME));
		this.cache.setCached(MOCK_CLASSNAME, cacheableMock);
		assertEquals(cacheableMock, this.cache.getCached(MOCK_CLASSNAME));
	}

	@Test
	public void hasCachedTest() {
		Cacheable cacheableMock = this.getCacheableMock();
		assertFalse(this.cache.hasCached(MOCK_CLASSNAME));
		this.cache.setCached(MOCK_CLASSNAME, cacheableMock);
		assertTrue(this.cache.hasCached(MOCK_CLASSNAME));
	}
}