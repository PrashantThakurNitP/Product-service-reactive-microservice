package com.reactive.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;

import com.reactive.productservice.dto.ProductDto;
import com.reactive.productservice.repository.ProductRepository;
import com.reactive.productservice.util.EntityDtoUtil;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	public Flux<ProductDto> getAll(){
		return this.repository.findAll()
		.map(EntityDtoUtil::toDto);
	}
	public Flux<ProductDto> getProductByPriceRange(int min, int max){
		return this.repository.findByPriceBetween(Range.closed(min, max))//we want to include min and max value
		.map(EntityDtoUtil::toDto);
	}
	public Mono<ProductDto> getProductById(String id){
		return this.repository.findById(id)
				.map(EntityDtoUtil::toDto);
	}
	
	public Mono<ProductDto> insertProduct(Mono<ProductDto> productDtoMono){
	return 	productDtoMono //getting product dto
		.map(EntityDtoUtil::toEntity) //convert to entity
		.flatMap(this.repository::insert)//insert this using reactor repository
		//after insertion it will be giving mono of updated entity with id 
		//we use flat map to extact that
		.map(EntityDtoUtil::toDto);
		//we again convert it back to dto
	}
	
	public Mono<ProductDto> updateProduct(String id, Mono<ProductDto>productDtoMono){
		
		//using findBiid ensure that is is present
		return this.repository.findById(id)
				//if it exist then we get Mono<Product>
				.flatMap(p-> productDtoMono
						.map(EntityDtoUtil::toEntity)
						//when we get dtu we convert it to entity
						.doOnNext(e->e.setId(id)))// once we get entoty we set id becuase id field can be blank
						.flatMap(this.repository::save)// it will be returning mono 
						.map(EntityDtoUtil::toDto); //convert to dto
	}
	public Mono<Void> deleteProduct(String id) {//when we donot use mono of void then it will not work
		return this.repository.deleteById(id);
	}
}
