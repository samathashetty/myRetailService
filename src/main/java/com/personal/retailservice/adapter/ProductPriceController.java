package com.personal.retailservice.adapter;/*
Created By samathashetty on 09/03/19
*/

import com.personal.retailservice.model.Item;
import com.personal.retailservice.model.Price;
import com.personal.retailservice.model.Product;
import com.personal.retailservice.model.ProductRequest;
import com.personal.retailservice.service.PriceService;
import com.personal.retailservice.service.RetailServiceImpl;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductPriceController {

    @RequestMapping(value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {

        ProductRequest pr = new RetailServiceImpl().getProductById(id);

        Price price = new PriceService().getPriceForProduct(id);

        Product p = new Product();
        p.id = id;
        p.name = pr.getItem().getProduct_description().getTitle();
        p.current_price = price;

        return new ResponseEntity<>(p, HttpStatus.OK);
    }
}
