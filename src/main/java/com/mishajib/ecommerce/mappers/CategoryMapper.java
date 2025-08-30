package com.mishajib.ecommerce.mappers;

import com.mishajib.ecommerce.dtos.CategoryDto;
import com.mishajib.ecommerce.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    Category toEntity(CategoryDto request);

    @Mapping(target = "id", ignore = true)
    void update(CategoryDto request, @MappingTarget Category category);
}
