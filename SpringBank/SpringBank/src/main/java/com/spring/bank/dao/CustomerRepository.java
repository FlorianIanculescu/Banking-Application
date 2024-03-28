package com.spring.bank.dao;

import com.spring.bank.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findCustomerByEmail(String email);
    Customer findCustomerByAccountNumber(String accountNumber);

    @Query(value = "SELECT * FROM customer WHERE username = ?1",
            nativeQuery = true)
    Customer findCustomerByUsername(String username);
}
