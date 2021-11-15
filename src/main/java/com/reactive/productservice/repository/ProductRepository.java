package com.reactive.productservice.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.reactive.productservice.entity.Product;
@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {//entity class and primary key
	
	

}
