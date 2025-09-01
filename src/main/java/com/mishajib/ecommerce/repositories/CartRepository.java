package com.mishajib.ecommerce.repositories;

import com.mishajib.ecommerce.entities.Cart;
import com.mishajib.ecommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {}