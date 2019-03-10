package com.personal.retailservice.adapter;/*
Created By samathashetty on 09/03/19
*/

import com.personal.retailservice.model.Price;
import com.personal.retailservice.model.Product;
import com.personal.retailservice.model.ProductRequest;
import com.personal.retailservice.model.ProductResponse;
import com.personal.retailservice.service.PriceService;
import com.personal.retailservice.service.RetailServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ProductPriceController {

    RetailServiceHelper retailServiceHelper;

    ProductPriceController(RetailServiceHelper rs){this.retailServiceHelper = rs;}

    @RequestMapping(value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {

        ProductRequest pr = retailServiceHelper.getProductById(id);

        Price price = retailServiceHelper.getPriceById(id);

        ProductResponse product = new ProductResponse();
        product.id = id;
        product.name = pr.getProduct().getItem().getProduct_description().getTitle();
        product.current_price = price;

        return new ResponseEntity(product, HttpStatus.OK);
    }

    @RequestMapping(value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ProductResponse updateProductPrice(@RequestBody ProductResponse product, HttpServletRequest request ) {


        Price price = retailServiceHelper.updatePrice(product.id, product.current_price.getPrice());

        ProductResponse updtProduct = new ProductResponse();
        updtProduct.id = product.id;
        updtProduct.name = product.name;
        updtProduct.current_price = price;

        return updtProduct;

    }

}
