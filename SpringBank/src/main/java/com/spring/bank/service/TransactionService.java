package com.spring.bank.service;

import com.spring.bank.entity.Customer;
import com.spring.bank.entity.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> findTransactionsById(Integer theId);

    List<Transaction> findTransactionByCustomerId(Integer theId);

    List<Transaction> findAllTransactions();

    void addNewTransaction(Customer theCustomer, int amount);
}
