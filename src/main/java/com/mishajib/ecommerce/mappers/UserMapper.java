package com.mishajib.ecommerce.mappers;

import com.mishajib.ecommerce.dtos.UserDto;
import com.mishajib.ecommerce.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    UserDto toDto(User user);
}
