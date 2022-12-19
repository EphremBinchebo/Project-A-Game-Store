package com.company.gamestore.repository;

import com.company.gamestore.model.Tshirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TShirtRepositoryTest {
    @Autowired
    TShirtRepository tShirtRepository;
    @Before
    public void setUp() throws Exception {
        tShirtRepository.deleteAll();
    }
    @Test
    public void shouldAddDeleteTshirt(){
        Tshirt tshirt = new Tshirt();
        tshirt.setSize("Large");
        tshirt.setColor("Red");
        tshirt.setDescription("Cotton tshirt");
        tshirt.setPrice(new BigDecimal("20.40"));
        tshirt.setQuantity(2);

        //Act
        tshirt = tShirtRepository.save(tshirt);

        //Act
        Optional<Tshirt> foundTshirt = tShirtRepository.findById(tshirt.getId());
        //Asser
        assertTrue(foundTshirt.isPresent());
        assertEquals(tshirt, foundTshirt.get());

        //Act
        tshirt.setQuantity(12);
        tshirt.setColor("White");

        tShirtRepository.save(tshirt);
        foundTshirt = tShirtRepository.findById(tshirt.getId());

        //Assert
        assertTrue(foundTshirt.isPresent());
        assertEquals(tshirt,foundTshirt.get());

        //Act
        tShirtRepository.deleteById(tshirt.getId());
        foundTshirt = tShirtRepository.findById(tshirt.getId());

        //Assert
        assertFalse(foundTshirt.isPresent());
    }
    @Test
    public void shouldGetAllTshirts(){
        //Arrange
        Tshirt tshirt1 = new Tshirt();
        tshirt1.setSize("Large");
        tshirt1.setColor("Red");
        tshirt1.setDescription("Cotton tshirt");
        tshirt1.setPrice(new BigDecimal("20.40"));
        tshirt1.setQuantity(2);

        Tshirt tshirt2 = new Tshirt();
        tshirt2.setSize("Large");
        tshirt2.setColor("Red");
        tshirt2.setDescription("Cotton tshirt");
        tshirt2.setPrice(new BigDecimal("20.40"));
        tshirt2.setQuantity(2);
        //Act
        tshirt1 = tShirtRepository.save(tshirt1);
        tshirt2 = tShirtRepository.save(tshirt2);

        //Act
        List<Tshirt> allTshirts = new ArrayList<>();
        allTshirts.add(tshirt1);
        allTshirts.add(tshirt2);

        //Act
        List<Tshirt> foundAllTshirt = tShirtRepository.findAll();

        //Assert
        assertEquals(foundAllTshirt, allTshirts);
    }
     @Test
    public void shouldFindAllTshirtByColor(){
         Tshirt tshirt1 = new Tshirt();
         tshirt1.setSize("Large");
         tshirt1.setColor("Red");
         tshirt1.setDescription("Cotton tshirt");
         tshirt1.setPrice(new BigDecimal("20.40"));
         tshirt1.setQuantity(2);

         Tshirt tshirt2 = new Tshirt();
         tshirt2.setSize("Large");
         tshirt2.setColor("Red");
         tshirt2.setDescription("Cotton tshirt");
         tshirt2.setPrice(new BigDecimal("20.40"));
         tshirt2.setQuantity(2);

         //Act
         tshirt1 = tShirtRepository.save(tshirt1);
         tshirt2 = tShirtRepository.save(tshirt2);

         //Act
         List<Tshirt> foundRedTshirt = tShirtRepository.findAllByColor("Red");
         List<Tshirt> foundWhiteTshirt = tShirtRepository.findAllByColor("White");

         //Assert
         assertEquals(foundRedTshirt.size(), 2);
         assertEquals(foundWhiteTshirt.size(), 0);
    }

    @Test
    public void shouldFindAllTshirtBySize(){
        Tshirt tshirt1 = new Tshirt();
        tshirt1.setSize("Large");
        tshirt1.setColor("Red");
        tshirt1.setDescription("Cotton tshirt");
        tshirt1.setPrice(new BigDecimal("20.40"));
        tshirt1.setQuantity(2);

        Tshirt tshirt2 = new Tshirt();
        tshirt2.setSize("Large");
        tshirt2.setColor("Red");
        tshirt2.setDescription("Cotton tshirt");
        tshirt2.setPrice(new BigDecimal("20.40"));
        tshirt2.setQuantity(2);

        //Act
        tshirt1 = tShirtRepository.save(tshirt1);
        tshirt2 = tShirtRepository.save(tshirt2);

        //Act
        List<Tshirt> foundLargeTshirt = tShirtRepository.findAllBySize("Large");
        List<Tshirt> foundSmallTshirt = tShirtRepository.findAllByColor("Small");

        //Assert
        assertEquals(foundLargeTshirt.size(), 2);
        assertEquals(foundSmallTshirt.size(), 0);
    }
}