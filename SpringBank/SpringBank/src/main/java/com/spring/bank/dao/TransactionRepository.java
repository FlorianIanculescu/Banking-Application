package com.spring.bank.dao;

import com.spring.bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findTransactionById(Integer theId);

    @Query(value = "SELECT * FROM transaction WHERE receiving_customer_id = ?1 OR sending_customer_id = ?1",
           nativeQuery = true)
    List<Transaction> findTransactionByCustomerId(Integer theId);
}
