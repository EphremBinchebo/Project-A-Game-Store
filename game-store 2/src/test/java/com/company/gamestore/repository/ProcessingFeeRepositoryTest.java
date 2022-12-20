package com.company.gamestore.repository;

import com.company.gamestore.model.ProcessingFee;
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
public class ProcessingFeeRepositoryTest {
    @Autowired
    ProcessingFeeRepository processingFeeRepository;
    @Before
    public void setUp() throws Exception {
        processingFeeRepository.deleteAll();
    }
    @Test
    public void getProcessingFee(){
        //Arrange
        ProcessingFee tshirtProcessingFee = new ProcessingFee();
        tshirtProcessingFee.setProductType("Tshirt");
        tshirtProcessingFee.setFee(new BigDecimal("12.00"));

        ProcessingFee gameProcessingFee = new ProcessingFee();
        gameProcessingFee.setProductType("Game");
        gameProcessingFee.setFee(new BigDecimal("20.00"));

        ProcessingFee consoleProcessingFee = new ProcessingFee();
        consoleProcessingFee.setProductType("Console");
        consoleProcessingFee.setFee(new BigDecimal("25.00"));

        //Act
        processingFeeRepository.save(tshirtProcessingFee);
        processingFeeRepository.save(gameProcessingFee);
        processingFeeRepository.save(consoleProcessingFee);

        //Assert
        Optional<ProcessingFee> foundTPFee;
        foundTPFee = processingFeeRepository.findById("Tshirt");
        assertTrue(foundTPFee.isPresent());
        assertEquals(foundTPFee.get().getFee(), new BigDecimal("12.00"));

        //Assert
        Optional<ProcessingFee> foundGFee;
        foundGFee = processingFeeRepository.findById("Game");
        assertTrue(foundGFee.isPresent());
        assertEquals(foundGFee.get().getFee(), new BigDecimal("20.00"));

        //Assert
        Optional<ProcessingFee> foundCFee;
        foundCFee = processingFeeRepository.findById("Console");
        assertTrue(foundCFee.isPresent());
        assertEquals(foundCFee.get().getFee(), new BigDecimal("25.00"));

    }
    @Test
    public void shouldGetProcessingFeeObject(){
        //Arrange
        ProcessingFee tshirtProcessingFee = new ProcessingFee();
        tshirtProcessingFee.setProductType("Tshirt");
        tshirtProcessingFee.setFee(new BigDecimal("12.00"));

        ProcessingFee gameProcessingFee = new ProcessingFee();
        gameProcessingFee.setProductType("Game");
        gameProcessingFee.setFee(new BigDecimal("20.00"));

        ProcessingFee consoleProcessingFee = new ProcessingFee();
        consoleProcessingFee.setProductType("Console");
        consoleProcessingFee.setFee(new BigDecimal("25.00"));

        //Act
        processingFeeRepository.save(tshirtProcessingFee);
        processingFeeRepository.save(gameProcessingFee);
        processingFeeRepository.save(consoleProcessingFee);

        //Act
        Optional<ProcessingFee> foundTfee = processingFeeRepository.findById("Tshirt");
        Optional<ProcessingFee> foundGfee = processingFeeRepository.findById("Game");
        Optional<ProcessingFee> foundCfee = processingFeeRepository.findById("Console");

        //Assert
        assertTrue(foundTfee.isPresent());
        assertEquals(tshirtProcessingFee, foundTfee.get());

        assertTrue(foundGfee.isPresent());
        assertEquals(gameProcessingFee, foundGfee.get());

        assertTrue(foundCfee.isPresent());
        assertEquals(consoleProcessingFee, foundCfee.get());

    }
}