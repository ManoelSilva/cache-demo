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
import com.demo.repository.DataRepository;

public class DataServiceTest extends DemoTest {
	@Mock
	private DataRepository repository;

	@InjectMocks
	private DataService service;

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.service = Mockito.spy(this.service);
	}

	@Test
	public void getDataTest() {
		when(this.repository.findById(ID_MOCK)).thenReturn(this.getDataMock());

		assertEquals(this.getDataMock().get(), this.service.getData(ID_MOCK));
		verify(this.repository, atLeastOnce()).findById(ID_MOCK);
	}

	@Test
	public void getDataEntityNotFoundTest() {
		when(this.repository.findById(ID_MOCK)).thenReturn(Optional.empty());

		assertThrows(EntityNotFoundException.class, () -> this.service.getData(ID_MOCK));
		verify(this.repository, atLeastOnce()).findById(ID_MOCK);
	}
}
