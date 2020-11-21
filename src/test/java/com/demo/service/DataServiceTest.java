package com.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.demo.DemoTest;
import com.demo.cache.Cache;
import com.demo.cache.CacheableModel;
import com.demo.repository.DataRepository;
import com.demo.repository.entity.Data;

public class DataServiceTest extends DemoTest {
	@Mock
	private DataRepository repository;

	@Mock
	private Cache cache;

	@InjectMocks
	private DataService service;

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.service = Mockito.spy(this.service);
	}

	@Test
	public void getDataTest() {
		Optional<Data> dataMock = this.getDataMock(NUMBER_ONE);
		when(this.repository.findById(NUMBER_ONE)).thenReturn(dataMock);
		assertEquals(dataMock.get(), this.service.getData(NUMBER_ONE));
		verify(this.repository, atLeastOnce()).findById(NUMBER_ONE);
	}

	@Test
	public void getDataCachedAndNotCachedYetTest() {
		Optional<Data> dataTwoMock = this.getDataMock(NUMBER_TWO);

		when(this.repository.findById(NUMBER_ONE)).thenReturn(this.getDataMock(NUMBER_ONE));
		when(this.repository.findById(NUMBER_TWO)).thenReturn(dataTwoMock);

		CacheableModel dataOneMockFirstCall = this.service.getData(NUMBER_ONE);
		CacheableModel dataOneMockSecondCall = this.service.getData(NUMBER_ONE);

		assertEquals(dataOneMockFirstCall, dataOneMockSecondCall);
		verify(this.repository, Mockito.times(NUMBER_ONE)).findById(NUMBER_ONE);

		assertEquals(dataTwoMock.get(), this.service.getData(NUMBER_TWO));
		verify(this.repository, atLeastOnce()).findById(NUMBER_TWO);
	}

	@Test
	public void getDataEntityNotFoundTest() {
		when(this.repository.findById(NUMBER_ONE)).thenReturn(Optional.empty());
		assertThrows(EntityNotFoundException.class, () -> this.service.getData(NUMBER_ONE));
		verify(this.repository, atLeastOnce()).findById(NUMBER_ONE);
	}
}
