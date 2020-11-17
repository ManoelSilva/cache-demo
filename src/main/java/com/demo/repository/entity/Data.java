package com.demo.repository.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.demo.cache.Cacheable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@lombok.Data
public class Data implements Cacheable {
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
}
