package com.mishajib.ecommerce.mappers;

import com.mishajib.ecommerce.dtos.CartDto;
import com.mishajib.ecommerce.dtos.CartItemDto;
import com.mishajib.ecommerce.dtos.CategoryDto;
import com.mishajib.ecommerce.entities.Cart;
import com.mishajib.ecommerce.entities.CartItem;
import com.mishajib.ecommerce.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartDto toDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    CartItemDto toDto(CartItem cartItem);
}
