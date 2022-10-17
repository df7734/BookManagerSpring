package com.example.bookmanager.repo;

import com.example.bookmanager.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}