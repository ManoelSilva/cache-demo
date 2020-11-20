package com.demo.controller;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpServerErrorException.ServiceUnavailable;

import com.demo.DemoTest;
import com.demo.cache.CacheableModel;
import com.demo.exception.ExceptionHandlerAdvice;
import com.demo.service.DataService;

public class DataControllerTest extends DemoTest {
	private MockMvc mvc;

	@Mock
	private DataService dataService;

	@InjectMocks
	private DataController dataController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(dataController).setControllerAdvice(new ExceptionHandlerAdvice()).build();
	}

	@Test
	public void getDataTest() throws Exception {
		CacheableModel cacheableMock = getCacheableMock();

		when(this.dataService.getData(ID_MOCK)).thenReturn(cacheableMock);

		mvc.perform(MockMvcRequestBuilders.get(API_DATA_URI.concat(String.valueOf(ID_MOCK))))
				.andExpect(MockMvcResultMatchers.status().isOk());
		verify(this.dataService, atLeastOnce()).getData(ID_MOCK);
	}

	@Test
	public void getDataNoContentTest() throws Exception {
		when(this.dataService.getData(ID_MOCK)).thenThrow(EntityNotFoundException.class);

		mvc.perform(MockMvcRequestBuilders.get(API_DATA_URI.concat(String.valueOf(ID_MOCK))))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
		verify(this.dataService, atLeastOnce()).getData(ID_MOCK);
	}

	@Test
	public void getDataServiceUnavailableException() throws Exception {
		when(this.dataService.getData(ID_MOCK)).thenThrow(ServiceUnavailable.class);

		mvc.perform(MockMvcRequestBuilders.get(API_DATA_URI.concat(String.valueOf(ID_MOCK))))
				.andExpect(MockMvcResultMatchers.status().is5xxServerError());
		verify(this.dataService, atLeastOnce()).getData(ID_MOCK);
	}
}
