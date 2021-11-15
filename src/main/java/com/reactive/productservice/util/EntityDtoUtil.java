package com.reactive.productservice.util;

import org.springframework.beans.BeanUtils;

import com.reactive.productservice.dto.ProductDto;
import com.reactive.productservice.entity.Product;

public class EntityDtoUtil {
	//convert to Dto from entity
		public static ProductDto toDto(Product product) {
			ProductDto dto = new ProductDto();
			//dto.setDescription(product.getDescription());
			BeanUtils.copyProperties(product, dto);
			return dto;
		}
		public static Product toEntity(ProductDto productDto) {
			Product product = new Product();
			//dto.setDescription(product.getDescription());
			BeanUtils.copyProperties(productDto, product);
			return product;
		}
}
