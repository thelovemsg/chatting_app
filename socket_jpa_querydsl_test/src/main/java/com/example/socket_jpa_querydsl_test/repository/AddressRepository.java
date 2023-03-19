package com.example.socket_jpa_querydsl_test.repository;

import com.example.socket_jpa_querydsl_test.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {
    List<Address> findAllByMemberId(String memberId);
}
