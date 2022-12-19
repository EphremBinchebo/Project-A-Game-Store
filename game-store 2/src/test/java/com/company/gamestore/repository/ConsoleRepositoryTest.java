package com.company.gamestore.repository;

import com.company.gamestore.model.Console;
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
public class ConsoleRepositoryTest {
    @Autowired
    ConsoleRepository consoleRepository;

    @Before
    public void setUp() throws Exception {
        consoleRepository.deleteAll();
    }

    @Test
    public void shouldAddFindDeleteConsole() {
        //Arrange
        Console newCon = new Console();
        newCon.setQuantity(1);
        newCon.setModel("P#");
        newCon.setManufacturer("Sega");
        newCon.setMemoryAmount("2GB");
        newCon.setProcessor("AMD");
        newCon.setPrice(new BigDecimal("10.05"));
        //Act
        newCon = consoleRepository.save(newCon);
        Optional<Console> foundCon = consoleRepository.findById(newCon.getId());

        //Assert
        assertTrue(foundCon.isPresent());
        assertEquals(newCon, foundCon.get());

        //Arrange
        newCon.setQuantity(5);
        newCon.setProcessor("Intl");

        //Act
        consoleRepository.save(newCon);
        foundCon = consoleRepository.findById(newCon.getId());

        //Assert
        assertTrue(foundCon.isPresent());
        assertEquals(newCon, foundCon.get());

        //Act
        consoleRepository.deleteById(newCon.getId());
        foundCon = consoleRepository.findById(newCon.getId());

        //Assert
        assertFalse(foundCon.isPresent());
    }

    @Test
    public void shouldFindAllConsole(){
        //Arrange
        Console newCon1 = new Console();
        newCon1.setQuantity(1);
        newCon1.setModel("P#");
        newCon1.setManufacturer("Sega");
        newCon1.setMemoryAmount("2GB");
        newCon1.setProcessor("AMD");
        newCon1.setPrice(new BigDecimal("10.05"));


        Console newCon2 = new Console();
        newCon2.setQuantity(5);
        newCon2.setModel("S#");
        newCon2.setManufacturer("Mega");
        newCon2.setMemoryAmount("6GB");
        newCon2.setProcessor("SONY");
        newCon2.setPrice(new BigDecimal("20.05"));

        //Act
        newCon1 = consoleRepository.save(newCon1);
        newCon2 = consoleRepository.save(newCon2);

        List<Console> allConsole = new ArrayList<>();
        allConsole.add(newCon1);
        allConsole.add(newCon2);
        //Act
        List<Console> foundAllConsole = consoleRepository.findAll();

        //Assert
        assertEquals(foundAllConsole, allConsole);

    }

    @Test
    public void shouldFindAllConsoleByManufacturer(){
        //Arrange
        Console newCon1 = new Console();
        newCon1.setQuantity(1);
        newCon1.setModel("P#");
        newCon1.setManufacturer("Mega");
        newCon1.setMemoryAmount("2GB");
        newCon1.setProcessor("AMD");
        newCon1.setPrice(new BigDecimal("10.05"));


        Console newCon2 = new Console();
        newCon2.setQuantity(5);
        newCon2.setModel("S#");
        newCon2.setManufacturer("Mega");
        newCon2.setMemoryAmount("6GB");
        newCon2.setProcessor("SONY");
        newCon2.setPrice(new BigDecimal("20.05"));

        //Act
        newCon1 = consoleRepository.save(newCon1);
        newCon2 = consoleRepository.save(newCon2);



        //Act
        List<Console> foundAllConsoleByManufacturer = consoleRepository.findAllByManufacturer("Mega");
        List<Console> zeroConsole = consoleRepository.findAllByManufacturer("AMD");

        //Assert

        assertEquals(foundAllConsoleByManufacturer.size(), 2);
        assertEquals(zeroConsole.size(), 0);
    }
}