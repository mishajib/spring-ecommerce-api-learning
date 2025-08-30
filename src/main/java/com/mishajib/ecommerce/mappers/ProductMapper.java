package com.mishajib.ecommerce.mappers;

import com.mishajib.ecommerce.dtos.ProductDto;
import com.mishajib.ecommerce.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "categoryId", source = "category.id")
    ProductDto toDto(Product product);

    Product toEntity(ProductDto request);

    @Mapping(target = "id", ignore = true)
    void update(ProductDto request, @MappingTarget Product product);
}
