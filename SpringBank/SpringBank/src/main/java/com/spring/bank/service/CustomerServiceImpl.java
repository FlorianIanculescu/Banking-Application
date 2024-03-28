package com.spring.bank.service;

import com.spring.bank.dao.CustomerRepository;
import com.spring.bank.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository theCustomerRepository) {
        this.customerRepository = theCustomerRepository;
    }

    @Override
    public Customer findById(int theId) {
        Optional<Customer> result = customerRepository.findById(theId);

        Customer theCustomer = null;

        if (result.isPresent()) {
            theCustomer = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + theId);
        }

        return theCustomer;
    }

    @Override
    public void save(Customer theCustomer) {
        theCustomer.setAccountNumber(generateAccountNumber());
        theCustomer.setBalance(1000);
        customerRepository.save(theCustomer);
    }


    public String generateAccountNumber() {

        Random random = new Random();
        StringBuilder prefix = new StringBuilder("ROSPR");

        prefix.append(random.nextInt(999999, 9999999));

        if (customerRepository.findCustomerByAccountNumber(prefix.toString()) == null) {
            return prefix.toString();
        } else {
            return generateAccountNumber();
        }
    }

    @Override
    public void depositMoney(Customer formCustomer, Customer theCustomer) {

        Customer dbCustomer = theCustomer;

        dbCustomer.setBalance(theCustomer.getBalance() + formCustomer.getBalance());

        customerRepository.save(dbCustomer);
    }

    @Override
    public void withdrawMoney(Customer formCustomer, Customer theCustomer) {

        Customer dbCustomer = theCustomer;

        dbCustomer.setBalance(theCustomer.getBalance() - formCustomer.getBalance());

        customerRepository.save(dbCustomer);
    }

    @Override
    public Customer findCustomerByUsername(String username) {

        Customer result = customerRepository.findCustomerByUsername(username);

        Customer customer = null;

        if (result != null) {
            customer = result;
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find transaction id - " + username);
        }

        return customer;
    }
}
