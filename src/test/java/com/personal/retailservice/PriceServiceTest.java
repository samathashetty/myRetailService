package com.personal.retailservice;/*
Created By samathashetty on 17/03/19
*/

import com.personal.retailservice.model.Price;
import com.personal.retailservice.service.PriceRepository;
import com.personal.retailservice.service.PriceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class PriceServiceTest {


@TestConfiguration
static class PriceServiceImplTestContextConfiguration {

    @Bean
    public PriceService priceService(PriceRepository pr) {
        return new PriceService(pr);
    }

}

    @Autowired
    private PriceService priceService;

    @MockBean
    private PriceRepository priceRepository;


    @Before
    public void setUp() {
        Price price = new Price();
        price.setId(123);
        price.setCurrency_code("USD");
        price.setPrice(123.0);

        Mockito.when(priceRepository.findById(price.getId())).thenReturn(Optional.of(price));
    }

    @Test
    public void testPriceById() {
        Price found = priceService.getPriceForProduct(123l);

        assertThat(found.getPrice()).isEqualTo(123);
    }
}