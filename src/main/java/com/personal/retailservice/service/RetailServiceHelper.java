package com.personal.retailservice.service;/*
Created By samathashetty on 09/03/19
*/

import com.fasterxml.jackson.databind.JsonNode;
import com.personal.retailservice.model.Price;
import com.personal.retailservice.model.ProductRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class RetailServiceHelper {

    PriceService priceService;

    @Value("${product.service.url}")
    String uri;
    public RetailServiceHelper(PriceService  ps){
        this.priceService = ps;
    }


    public ProductRequest getProductById(Long id) {


        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id.toString());

        RestTemplate restTemplate = new RestTemplate(
                new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));

        ProductRequest result = restTemplate.getForObject(uri, ProductRequest.class, params);

        return result;
    }

    public Price getPriceById(Long id) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id.toString());

        return priceService.getPriceForProduct(id);

    }


    public Price updatePrice(long id, double price) {

        return priceService.updatePriceForProduct(id, price);
    }

}
