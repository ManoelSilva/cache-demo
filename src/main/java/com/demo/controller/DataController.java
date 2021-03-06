package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.cache.CacheableModel;
import com.demo.service.DataService;

@RestController
@RequestMapping("/api/data")
public class DataController {
	private final DataService dataService;

	@Autowired
	public DataController(DataService cacheService) {
		this.dataService = cacheService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<CacheableModel> getData(@PathVariable Integer id) {
		return ResponseEntity.ok(dataService.getData(id));
	}
}
