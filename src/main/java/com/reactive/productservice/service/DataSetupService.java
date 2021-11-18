package com.reactive.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.reactive.productservice.dto.ProductDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DataSetupService implements CommandLineRunner {
	
	@Autowired
	private ProductService service;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		ProductDto p1 = new ProductDto("4k-tv",1000);
		ProductDto p2 = new ProductDto("slr-camera",750);
		ProductDto p3 = new ProductDto("4k-tv",800);
		ProductDto p4 = new ProductDto("4k-tv",100);
		
		Flux.just(p1,p2,p3,p4)
			.flatMap(p->this.service.insertProduct(Mono.just(p)))
			.subscribe(System.out::println);
		
	}
	
	

}
