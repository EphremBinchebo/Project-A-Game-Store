package com.company.gamestore.repository;

import com.company.gamestore.model.Game;
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
public class GameRepositoryTest {
    @Autowired
    GameRepository gameRepository;
    @Before
    public void setUp() throws Exception {
        gameRepository.deleteAll();
    }

    @Test
    public void shouldAddFindDeleteGame(){
        //Arrange
        Game newGame = new Game();
        newGame.setTitle("SuperMario");
        newGame.setEsrbRating("10");
        newGame.setDescription("Action");
        newGame.setStudio("SONY");
        newGame.setPrice(new BigDecimal("10.80"));
        newGame.setQuantity(1);

        //Act
        newGame = gameRepository.save(newGame);
        Optional<Game> foundGame = gameRepository.findById(newGame.getId());

       // Act
        newGame.setQuantity(4);
        newGame.setTitle("FootBall");
        gameRepository.save(newGame);
        foundGame = gameRepository.findById(newGame.getId());

        //Assert
        assertTrue(foundGame.isPresent());
        assertEquals(newGame, foundGame.get());

        //Act
        gameRepository.deleteById(newGame.getId());
        Optional<Game> foundGame1 = gameRepository.findById(newGame.getId());

        //Assert
        assertFalse(foundGame1.isPresent());
    }

    @Test
    public void shouldFindAllGame(){
        //Arrange
        Game newGame1 = new Game();
        newGame1.setTitle("SuperMario");
        newGame1.setEsrbRating("10");
        newGame1.setDescription("Action");
        newGame1.setStudio("SONY");
        newGame1.setPrice(new BigDecimal("10.80"));
        newGame1.setQuantity(1);

        Game newGame2 = new Game();
        newGame2.setTitle("Avatar");
        newGame2.setEsrbRating("5");
        newGame2.setDescription("Adventure");
        newGame2.setStudio("SONY");
        newGame2.setPrice(new BigDecimal("10.80"));
        newGame2.setQuantity(1);

        //Act
        newGame1 = gameRepository.save(newGame1);
        newGame2 = gameRepository.save(newGame2);

        //Act
        List<Game> allGame = new ArrayList<>();
        allGame.add(newGame1);
        allGame.add(newGame2);

        //Act
        List<Game> foundAllGame = gameRepository.findAll();

        //Assert
        assertEquals(foundAllGame, allGame);
    }

    @Test
    public void shouldFindAllGameByEsrbRating(){
        //Arrange
        Game newGame1 = new Game();
        newGame1.setTitle("SuperMario");
        newGame1.setEsrbRating("8");
        newGame1.setDescription("Action");
        newGame1.setStudio("SONY");
        newGame1.setPrice(new BigDecimal("10.80"));
        newGame1.setQuantity(1);


        Game newGame2 = new Game();
        newGame2.setTitle("FIFA2020");
        newGame2.setEsrbRating("8");
        newGame2.setDescription("Action");
        newGame2.setStudio("GMM");
        newGame2.setPrice(new BigDecimal("10.80"));
        newGame2.setQuantity(2);

        Game newGame3 = new Game();
        newGame3.setTitle("FIFA2020");
        newGame3.setEsrbRating("5");
        newGame3.setDescription("Action");
        newGame3.setStudio("GMM");
        newGame3.setPrice(new BigDecimal("10.80"));
        newGame3.setQuantity(2);

        //Act
        newGame1 = gameRepository.save(newGame1);
        newGame2 = gameRepository.save(newGame2);
        newGame3 = gameRepository.save(newGame3);

        //Act
        List<Game> foundGameEsrbRating8 = gameRepository.findAllByEsrbRating("8");
        List<Game> foundGameEsrbRating5 = gameRepository.findAllByEsrbRating("5");
        List<Game> foundGameEsrbRating0 = gameRepository.findAllByEsrbRating("0");
        //Assert
        assertEquals(foundGameEsrbRating8.size(), 2);
        assertEquals(foundGameEsrbRating5.size(), 1);
        assertEquals(foundGameEsrbRating0.size(), 0);
    }

    @Test
    public void shouldFindAllGameByTitle(){
        //Arrange
        Game newGame1 = new Game();
        newGame1.setTitle("SuperMario");
        newGame1.setEsrbRating("8");
        newGame1.setDescription("Action");
        newGame1.setStudio("SONY");
        newGame1.setPrice(new BigDecimal("10.80"));
        newGame1.setQuantity(1);


        Game newGame2 = new Game();
        newGame2.setTitle("FIFA2020");
        newGame2.setEsrbRating("8");
        newGame2.setDescription("Action");
        newGame2.setStudio("GMM");
        newGame2.setPrice(new BigDecimal("10.80"));
        newGame2.setQuantity(2);

        Game newGame3 = new Game();
        newGame3.setTitle("FIFA2020");
        newGame3.setEsrbRating("5");
        newGame3.setDescription("Action");
        newGame3.setStudio("GMM");
        newGame3.setPrice(new BigDecimal("10.80"));
        newGame3.setQuantity(2);

        //Act
        newGame1 = gameRepository.save(newGame1);
        newGame2 = gameRepository.save(newGame2);
        newGame3 = gameRepository.save(newGame3);

        //Act
        List<Game> foundGameByTitle1 = gameRepository.findAllByTitle("FIFA2020");
        List<Game> foundGameByTitle2 = gameRepository.findAllByTitle("SuperMario");
        List<Game> foundGameByTitle3 = gameRepository.findAllByTitle("Avater");
        //Assert
        assertEquals(foundGameByTitle1.size(), 2);
        assertEquals(foundGameByTitle2.size(), 1);
        assertEquals(foundGameByTitle3.size(), 0);
    }

    @Test
    public void shouldFindAllGameByStudio(){
        //Arrange
        Game newGame1 = new Game();
        newGame1.setTitle("SuperMario");
        newGame1.setEsrbRating("8");
        newGame1.setDescription("Action");
        newGame1.setStudio("SONY");
        newGame1.setPrice(new BigDecimal("10.80"));
        newGame1.setQuantity(1);


        Game newGame2 = new Game();
        newGame2.setTitle("FIFA2020");
        newGame2.setEsrbRating("8");
        newGame2.setDescription("Action");
        newGame2.setStudio("GMM");
        newGame2.setPrice(new BigDecimal("10.80"));
        newGame2.setQuantity(2);

        Game newGame3 = new Game();
        newGame3.setTitle("FIFA2020");
        newGame3.setEsrbRating("5");
        newGame3.setDescription("Action");
        newGame3.setStudio("GMM");
        newGame3.setPrice(new BigDecimal("10.80"));
        newGame3.setQuantity(2);

        //Act
        newGame1 = gameRepository.save(newGame1);
        newGame2 = gameRepository.save(newGame2);
        newGame3 = gameRepository.save(newGame3);

        //Act
        List<Game> foundGameByTitle1 = gameRepository.findAllByStudio("GMM");
        List<Game> foundGameByTitle2 = gameRepository.findAllByStudio("SONY");
        List<Game> foundGameByTitle3 = gameRepository.findAllByStudio("ABC");
        //Assert
        assertEquals(foundGameByTitle1.size(), 2);
        assertEquals(foundGameByTitle2.size(), 1);
        assertEquals(foundGameByTitle3.size(), 0);
    }
}