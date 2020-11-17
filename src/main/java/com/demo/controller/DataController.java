package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.cache.Cacheable;
import com.demo.service.CacheService;

@RestController
@RequestMapping("/api/data")
public class DataController {
	private final CacheService cacheService;

	@Autowired
	public DataController(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cacheable> getData(@PathVariable Integer id) {
		return ResponseEntity.ok(cacheService.getData(id));
	}
}
