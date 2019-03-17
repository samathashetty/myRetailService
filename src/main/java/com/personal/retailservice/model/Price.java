package com.personal.retailservice.model;/*
Created By samathashetty on 09/03/19
*/

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Price {
    @Id
    private long id;
    private double price;
    private String currency_code;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }


}
