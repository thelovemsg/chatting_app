package com.example.socket_jpa_querydsl_test.repository;

import com.example.socket_jpa_querydsl_test.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {
}
