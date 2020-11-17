package com.demo.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.repository.DataRepository;
import com.demo.repository.entity.Data;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DataService {
	private static final String FETCHING_DATA_MSG = "Fetching data from database..";
	private final DataRepository dataRepository;

	@Autowired
	public DataService(DataRepository dataRepository) {
		this.dataRepository = dataRepository;
	}

	public Data getData(Integer id) {
		log.info(FETCHING_DATA_MSG);
		Optional<Data> data = this.dataRepository.findById(id);
		if (!data.isPresent()) {
			throw new EntityNotFoundException();
		}
		return data.get();
	}
}
