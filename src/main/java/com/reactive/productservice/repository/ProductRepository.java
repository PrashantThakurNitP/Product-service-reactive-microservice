package com.reactive.productservice.repository;

import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.reactive.productservice.entity.Product;

import reactor.core.publisher.Flux;
@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {//entity class and primary key
	
	//Flux<Product>findBYPriceBetween(int min ,int max);//it give >min & <max
	//above will not give desired output
	
	//we will use spring range
	Flux<Product>findByPriceBetween(Range<Integer>range);//here we can prodvide incluse exclusive range

}
