package com.mishajib.ecommerce.mappers;

import com.mishajib.ecommerce.dtos.UserDto;
import com.mishajib.ecommerce.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
}
