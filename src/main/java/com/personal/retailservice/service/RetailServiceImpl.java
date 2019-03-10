package com.personal.retailservice.service;/*
Created By samathashetty on 09/03/19
*/

import com.personal.retailservice.model.Price;
import com.personal.retailservice.model.ProductRequest;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class RetailServiceImpl implements RetailService {
    @Override
    public ProductRequest getProductById(Long id) {

        final String uri = "http://redsky.target.com/v2/pdp/tcin/{id}?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";

        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id.toString());

        RestTemplate restTemplate = new RestTemplate();
        ProductRequest result = restTemplate.getForObject(uri, ProductRequest.class, params);

        return result;
    }

    public Price getPriceById(Long id) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id.toString());


        PriceService priceService = new PriceService();
        return priceService.getPriceForProduct(id);

    }


    }
