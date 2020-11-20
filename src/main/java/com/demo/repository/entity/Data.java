package com.demo.repository.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.demo.cache.CacheableModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@lombok.Data
public class Data implements CacheableModel {
	@Transient
	@JsonIgnore
	private Long timeToRenewCache = 10 * 3000L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String message;

	@Override
	public Long getCacheTimeCriteria() {
		return this.timeToRenewCache;
	}

	public Data(Integer id, String message) {
		this.id = id;
		this.message = message;
	}
}
