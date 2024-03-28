package com.spring.bank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Min(value = 0, message = "Amount must be greater than 0")
    @Column(name = "amount")
    private int amount;

    @Column(name = "date")
    private String date;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiving_customer_id")
    private Customer sendingCustomer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sending_customer_id")
    private Customer receivingCustomer;

    public Transaction() {

    }

    public Transaction(int amount, String date, String description, Customer sendingCustomer, Customer receivingCustomer) {
        this.amount = amount;
        this.date = getCurrentTime();
        this.description = description;
        this.sendingCustomer = sendingCustomer;
        this.receivingCustomer = receivingCustomer;
    }

    private String getCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")
                .withZone(ZoneId.of("Europe/Bucharest"));
        Instant instant = Instant.now();
        return formatter.format(instant);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Customer getSendingCustomer() {
        return sendingCustomer;
    }

    public void setSendingCustomer(Customer sendingCustomer) {
        this.sendingCustomer = sendingCustomer;
    }

    public Customer getReceivingCustomer() {
        return receivingCustomer;
    }

    public void setReceivingCustomer(Customer receivingCustomer) {
        this.receivingCustomer = receivingCustomer;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
