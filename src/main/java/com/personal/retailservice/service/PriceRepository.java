package com.personal.retailservice.service;/*
Created By samathashetty on 09/03/19
*/


import com.personal.retailservice.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> { }
