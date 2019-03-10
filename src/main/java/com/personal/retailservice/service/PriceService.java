package com.personal.retailservice.service;/*
Created By samathashetty on 09/03/19
*/

import com.personal.retailservice.model.Price;
import org.springframework.stereotype.Service;

@Service("price")
public class PriceService {


    private  PriceRepository priceRepository;
    public Price getPriceForProduct(Long id){

        return priceRepository.findById(id).get();

    }

}
