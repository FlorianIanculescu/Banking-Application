package com.spring.bank.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="username")
    private String username;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="account_number")
    private String accountNumber;

    @Column(name="password")
    private String password;

    @Column(name="balance")
    private int balance;

    @OneToMany(mappedBy = "sendingCustomer", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                                                        CascadeType.DETACH, CascadeType.REFRESH})
    private List<Transaction> sendingTransactions;

    @OneToMany(mappedBy = "receivingCustomer", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                                                          CascadeType.DETACH, CascadeType.REFRESH})
    private List<Transaction> receivingTransactions;

    public Customer() {

    }

    public Customer(String username, String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<Transaction> getSendingTransactions() {
        return sendingTransactions;
    }

    public void setSendingTransactions(List<Transaction> sendingTransactions) {
        this.sendingTransactions = sendingTransactions;
    }

    public List<Transaction> getReceivingTransactions() {
        return receivingTransactions;
    }

    public void setReceivingTransactions(List<Transaction> receivingTransactions) {
        this.receivingTransactions = receivingTransactions;
    }


    public void addSendingTransactions(Transaction tempTransaction) {

        if (sendingTransactions == null) {
            sendingTransactions = new ArrayList<>();
        }
        sendingTransactions.add(tempTransaction);

        tempTransaction.setSendingCustomer(this);
    }

    public void addReceivingTransactions(Transaction tempTransaction) {

        if (receivingTransactions == null) {
            receivingTransactions = new ArrayList<>();
        }
        receivingTransactions.add(tempTransaction);

        tempTransaction.setReceivingCustomer(this);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", sendingTransactions=" + sendingTransactions +
                ", receivingTransactions=" + receivingTransactions +
                '}';
    }
}
