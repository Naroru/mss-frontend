package com.example.mssfrontend.model.employee.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  List<Customer> findByLastNameStartsWithIgnoreCase(String lastName);
}
