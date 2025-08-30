package com.mishajib.ecommerce.repositories;

import com.mishajib.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
