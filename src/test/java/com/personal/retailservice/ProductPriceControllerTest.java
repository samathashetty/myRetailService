package com.personal.retailservice;/*
Created By samathashetty on 14/03/19
*/

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.retailservice.adapter.ProductPriceController;
import com.personal.retailservice.model.Price;
import com.personal.retailservice.model.ProductRequest;
import com.personal.retailservice.service.RetailServiceHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductPriceController.class)
//@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
public class ProductPriceControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RetailServiceHelper retailServiceHelper;

    Price price;
    ProductRequest productRequest;

    @Before
    public void setup() throws Exception{
        price = new Price();
        price.setId(123456L);
        price.setPrice(1234);
        price.setCurrency_code("USD");
        String productRequestJson = "{\n" +
                "    \"product\": {\n" +
                "        \"item\": {\n" +
                "            \"product_description\": {\n" +
                "                \"title\": \"The Big Lebowski (Blu-ray)\"" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";
        productRequest = new ObjectMapper().readValue(productRequestJson, ProductRequest.class);

    }

    @Test
    public void testGetProductPricePresent() throws Exception{

        given(retailServiceHelper.getPriceById(123456L)).willReturn(price);
        given(retailServiceHelper.getProductById(123456L)).willReturn(productRequest);


        mvc.perform(get("/products/123456")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.name").value(productRequest.getProduct().getItem().getProduct_description().getTitle()))
                .andExpect(jsonPath("$.current_price.price").value(price.getPrice()));

    }

    @Test
    public void testGetProductAbsent() throws Exception{

        given(retailServiceHelper.getPriceById(123456L)).willReturn(price);
        given(retailServiceHelper.getProductById(123456L)).willReturn(null);


        mvc.perform(get("/products/123456")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());


    }
    @Test
    public void testUpdateProduct() throws Exception{

        String updateReqJson = "{\n" +
                "    \"id\": 123456,\n" +
                "    \"name\": \"The Big Lebowski (Blu-ray)\",\n" +
                "    \"current_price\": {\n" +
                "        \"id\": 123456,\n" +
                "        \"price\": 1234,\n" +
                "        \"currency_code\": \"USD\"\n" +
                "    }\n" +
                "}";

        Price updtPrice = new Price();
        updtPrice.setId(123456);
        updtPrice.setCurrency_code("USD");
        updtPrice.setPrice(1234);
        given(retailServiceHelper.getPriceById(123456L)).willReturn(price);
        given(retailServiceHelper.updatePrice(123456L,1234)).willReturn(updtPrice);


        mvc.perform(post("/products/123456")
                .content(updateReqJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.current_price.price").value(1234));

    }

    @Test
    public void testUpdateProductIdAbsent() throws Exception{

        String updateReqJson = "{\n" +
                "    \"id\": 123456,\n" +
                "    \"name\": \"The Big Lebowski (Blu-ray)\",\n" +
                "    \"current_price\": {\n" +
                "        \"id\": 123456,\n" +
                "        \"price\": 1234,\n" +
                "        \"currency_code\": \"USD\"\n" +
                "    }\n" +
                "}";

        given(retailServiceHelper.getPriceById(123456L)).willReturn(null);

        mvc.perform(post("/products/123456")
                .content(updateReqJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testUpdateProductAbsent() throws Exception{


        given(retailServiceHelper.getPriceById(123456L)).willReturn(null);

        mvc.perform(post("/products/123456")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void testUpdateProductMalformed() throws Exception{

        String updateReqJson = "{\n" +
                "    \"id\": 123456,\n" +
                "    \"name\": \"The Big Lebowski (Blu-ray)\",\n" +
                "    \"current_price\": {\n" +
                "        \"id\": 12345,\n" +
                "        \"price\": 1234,\n" +
                "        \"currency_code\": \"USD\"\n" +
                "    }\n" +
                "}";

        given(retailServiceHelper.getPriceById(123456L)).willReturn(null);

        mvc.perform(post("/products/123456")
                .content(updateReqJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }
}
