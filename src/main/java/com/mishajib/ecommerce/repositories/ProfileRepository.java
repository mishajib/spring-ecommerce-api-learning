package com.mishajib.ecommerce.repositories;

import com.mishajib.ecommerce.entities.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}