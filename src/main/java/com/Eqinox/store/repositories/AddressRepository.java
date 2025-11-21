package com.Eqinox.store.repositories;

import com.Eqinox.store.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}