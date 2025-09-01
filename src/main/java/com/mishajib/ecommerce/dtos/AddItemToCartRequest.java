package com.mishajib.ecommerce.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class AddItemToCartRequest {
    @NotNull(message = "Product is required!")
    Long productId;
}