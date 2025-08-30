package com.mishajib.ecommerce.repositories;

import com.mishajib.ecommerce.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
