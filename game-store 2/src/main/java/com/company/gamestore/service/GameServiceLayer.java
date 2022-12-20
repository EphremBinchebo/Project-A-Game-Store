package com.company.gamestore.service;

import com.company.gamestore.model.Game;
import com.company.gamestore.repository.*;
import com.company.gamestore.viewModel.GameViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class GameServiceLayer {

    GameRepository gameRepository;
    ConsoleRepository consoleRepository;
    TShirtRepository tShirtRepository;
    InvoiceRepository invoiceRepository;
    ProcessingFeeRepository processingFeeRepository;
    TaxRepository taxRepository;
    @Autowired
    public GameServiceLayer(GameRepository gameRepository, ConsoleRepository consoleRepository, TShirtRepository tShirtRepository, TaxRepository taxRepository, ProcessingFeeRepository processingFeeRepository, InvoiceRepository invoiceRepository) {
        this.gameRepository = gameRepository;
        this.consoleRepository = consoleRepository;
        this.tShirtRepository = tShirtRepository;
        this.invoiceRepository = invoiceRepository;
        this.taxRepository = taxRepository;
        this.processingFeeRepository = processingFeeRepository;
    }

    public GameViewModel createGame(GameViewModel gameViewModel) {
        if(gameViewModel == null) throw new IllegalArgumentException("No Game is passed! Game object is null!");
        Game game = new Game();
        game.setTitle(gameViewModel.getTitle());
        game.setEsrbRating(gameViewModel.getEsrbRating());
        game.setStudio(gameViewModel.getStudio());
        game.setPrice(gameViewModel.getPrice());
        game.setDescription(gameViewModel.getDescription());
        game.setQuantity(gameViewModel.getQuantity());
        gameViewModel.setId(gameRepository.save(game).getId());
        return gameViewModel;
    }

    public GameViewModel getGame(long id) {
        Optional<Game> game = gameRepository.findById(id);

        if(game == null){
            return null;
        }else{
            return buildGameViewModel(game.get());
        }
    }

    public GameViewModel buildGameViewModel(Game game) {

        GameViewModel gameViewModel = new GameViewModel();
        gameViewModel.setId(game.getId());
        gameViewModel.setTitle(game.getTitle());
        gameViewModel.setEsrbRating(game.getEsrbRating());
        gameViewModel.setDescription(game.getDescription());
        gameViewModel.setPrice(game.getPrice());
        gameViewModel.setStudio(game.getStudio());
        gameViewModel.setQuantity(game.getQuantity());

        return gameViewModel;
    }

//    public GameViewModel createGame(GameViewModel gameViewModel) {
//        return
//    }
    // private final BigDecimal PROCESSING_FEE = new BigDecimal("15.49")
}
