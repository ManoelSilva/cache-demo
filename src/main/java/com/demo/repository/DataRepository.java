package com.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.repository.entity.Data;

public interface DataRepository extends CrudRepository<Data, Integer> {

}
