package com.mishajib.ecommerce.repositories;

import com.mishajib.ecommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Byte> {
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Byte id);
}