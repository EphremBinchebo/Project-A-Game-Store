package com.company.gamestore.repository;

import com.company.gamestore.model.Tax;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaxRepositoryTest {
    @Autowired
    TaxRepository taxRepository;

    Tax[] states = new Tax[]{
            new Tax("AK",new BigDecimal("0.06")),
            new Tax("AL",new BigDecimal("0.05")),
            new Tax("AR",new BigDecimal("0.06")),
            new Tax("AZ",new BigDecimal("0.04")),
            new Tax("CA",new BigDecimal("0.06")),
            new Tax("CO",new BigDecimal("0.04")),
            new Tax("CT",new BigDecimal("0.03")),
            new Tax("DE",new BigDecimal("0.05")),
            new Tax("FL",new BigDecimal("0.06")),
            new Tax("GA",new BigDecimal("0.07")),
            new Tax("HI",new BigDecimal("0.05")),
            new Tax("IA",new BigDecimal("0.04")),
            new Tax("ID",new BigDecimal("0.03")),
            new Tax("IL",new BigDecimal("0.05")),
            new Tax("IN",new BigDecimal("0.05")),
            new Tax("KS",new BigDecimal("0.06")),
            new Tax("KY",new BigDecimal("0.04")),
            new Tax("LA",new BigDecimal("0.05")),
            new Tax("MA",new BigDecimal("0.05")),
            new Tax("MD",new BigDecimal("0.07")),
            new Tax("ME",new BigDecimal("0.03")),
            new Tax("MI",new BigDecimal("0.06")),
            new Tax("MN",new BigDecimal("0.06")),
            new Tax("MO",new BigDecimal("0.05")),
            new Tax("MS",new BigDecimal("0.05")),
            new Tax("MT",new BigDecimal("0.03")),
            new Tax("NC",new BigDecimal("0.05")),
            new Tax("ND",new BigDecimal("0.05")),
            new Tax("NE",new BigDecimal("0.04")),
            new Tax("NH",new BigDecimal("0.06")),
            new Tax("NJ",new BigDecimal("0.05")),
            new Tax("NM",new BigDecimal("0.05")),
            new Tax("NV",new BigDecimal("0.04")),
            new Tax("NY",new BigDecimal("0.06")),
            new Tax("OH",new BigDecimal("0.04")),
            new Tax("OK",new BigDecimal("0.04")),
            new Tax("OR",new BigDecimal("0.07")),
            new Tax("PA",new BigDecimal("0.06")),
            new Tax("RI",new BigDecimal("0.06")),
            new Tax("SC",new BigDecimal("0.06")),
            new Tax("SD",new BigDecimal("0.06")),
            new Tax("TN",new BigDecimal("0.05")),
            new Tax("TX",new BigDecimal("0.03")),
            new Tax("UT",new BigDecimal("0.04")),
            new Tax("VA",new BigDecimal("0.06")),
            new Tax("VT",new BigDecimal("0.07")),
            new Tax("WA",new BigDecimal("0.05")),
            new Tax("WI",new BigDecimal("0.03")),
            new Tax("WV",new BigDecimal("0.05")),
            new Tax("WY",new BigDecimal("0.04"))
    };
    @Test
    public void getTaxRateObject() {
        Optional<Tax> foundTax;

        for (Tax statSalesTax :
                states) {
            foundTax = taxRepository.findById(statSalesTax.getState());
            assertTrue(foundTax.isPresent());
            assertEquals(foundTax.get(), statSalesTax);

        }
    }
    @Test
    public void getTaxRate(){
            Optional<Tax> foundTax;

            for(Tax stateSalesTax :
            states){
                foundTax = taxRepository.findById(stateSalesTax.getState());
                assertTrue(foundTax.isPresent());
                assertEquals(foundTax.get(), stateSalesTax);
            }
        }
    }
