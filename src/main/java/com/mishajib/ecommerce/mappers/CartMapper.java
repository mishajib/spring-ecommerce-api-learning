package com.mishajib.ecommerce.mappers;

import com.mishajib.ecommerce.dtos.CartDto;
import com.mishajib.ecommerce.dtos.CartItemDto;
import com.mishajib.ecommerce.entities.Cart;
import com.mishajib.ecommerce.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "totalPrice", expression = "java(cart.getTotalPrice())")
    CartDto toDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    CartItemDto toDto(CartItem cartItem);
}
