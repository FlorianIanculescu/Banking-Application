package com.spring.bank.service;

import com.spring.bank.entity.Customer;

public interface CustomerService {

    Customer findById(int theId);

    void save(Customer theCustomer);

    void depositMoney(Customer formCustomer, Customer theCustomer);

    void withdrawMoney(Customer formCustomer, Customer theCustomer);

    Customer findCustomerByUsername(String username);
}
