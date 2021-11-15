package com.reactive.productservice.dto;

import lombok.Data;
import lombok.ToString;

@Data//getter and setter
@ToString
public class ProductDto {

	private String id;
	private String description;
	private Integer price;
}
