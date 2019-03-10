package com.personal.retailservice.service;/*
Created By samathashetty on 09/03/19
*/

import com.personal.retailservice.model.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("price")
public class PriceService {

    PriceService(PriceRepository priceRepository){
        this.priceRepository = priceRepository;
    }

    private final   PriceRepository priceRepository;
    public Price getPriceForProduct(Long id){

        Optional<Price> p =priceRepository.findById(id);
        return p.get();

    }


    public Price updatePriceForProduct(Long id, double price){

        Price p =priceRepository.findById(id).orElse(null);

        p.setPrice(price);

        priceRepository.save(p);

        return p;

    }

}
