package com.mishajib.ecommerce.mappers;

import com.mishajib.ecommerce.dtos.RegisterUserRequest;
import com.mishajib.ecommerce.dtos.UpdateUserRequest;
import com.mishajib.ecommerce.dtos.UserDto;
import com.mishajib.ecommerce.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(RegisterUserRequest request);

    void update(UpdateUserRequest request, @MappingTarget User user);
}
