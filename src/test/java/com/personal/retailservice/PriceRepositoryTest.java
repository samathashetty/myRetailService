package com.personal.retailservice;/*
Created By samathashetty on 17/03/19
*/

import com.personal.retailservice.model.Price;
import com.personal.retailservice.service.PriceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PriceRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PriceRepository priceRepository;

    @Test
    public void testFindById() {
        // given
        Price price = new Price();
        price.setPrice(123.0);
        price.setCurrency_code("USD");
        price.setId(98);
        entityManager.persist(price);
        entityManager.flush();

        // when
        Price found = priceRepository.findById(price.getId()).get();

        // then
        assertThat(found.getPrice()).isEqualTo(price.getPrice());
    }
}
