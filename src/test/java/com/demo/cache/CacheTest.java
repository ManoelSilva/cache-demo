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
}