package com.mishajib.ecommerce.repositories;

import com.mishajib.ecommerce.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}