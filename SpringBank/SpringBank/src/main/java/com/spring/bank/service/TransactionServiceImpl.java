package com.spring.bank.service;

import com.spring.bank.dao.TransactionRepository;
import com.spring.bank.entity.Customer;
import com.spring.bank.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    private CustomerService customerService;

    @Autowired
    public TransactionServiceImpl(TransactionRepository theTransactionRepository,
                                  CustomerService theCustomerService) {
        this.transactionRepository = theTransactionRepository;
        this.customerService = theCustomerService;
    }

    @Override
    public List<Transaction> findTransactionsById(Integer theId) {

        List<Transaction> result = transactionRepository.findTransactionById(theId);

        List<Transaction> theTransactions = null;

        if (result != null) {
            theTransactions = result;
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find transaction id - " + theId);
        }

        return theTransactions;
    }

    @Override
    public List<Transaction> findTransactionByCustomerId(Integer theId) {

        List<Transaction> result = transactionRepository.findTransactionByCustomerId(theId);

        List<Transaction> transactions = null;

        if (result != null) {
            transactions = result;
        } else {
            throw new RuntimeException("Did not found any transaction for customerID = " + theId);
        }

        return transactions;
    }

    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public void addNewTransaction(Customer theCustomer, int amount) {

        Transaction newTransaction = new Transaction();
        newTransaction.setDate(LocalDateTime.now().toString());
        newTransaction.setAmount(amount);
        newTransaction.setSendingCustomer(theCustomer);
        newTransaction.setReceivingCustomer(theCustomer);
        newTransaction.setDescription("Descriere");

        transactionRepository.save(newTransaction);
    }
}
