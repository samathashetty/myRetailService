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
    private CurrencyCode currency_code;
}
