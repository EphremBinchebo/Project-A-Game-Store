package com.company.gamestore.service;

import com.company.gamestore.model.*;
import com.company.gamestore.repository.*;
import com.company.gamestore.viewModel.GameViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
public class GameServiceLayerTest {
    ConsoleRepository consoleRepository;
    GameRepository gameRepository;
    TShirtRepository tShirtRepository;
    InvoiceRepository invoiceRepository;
    ProcessingFeeRepository processingFeeRepository;
    TaxRepository taxRepository;

    GameServiceLayer service;

    @Before
    public void setUp() throws Exception {
    setUpConsoleRepositoryMock();
    setUpGameRepositoryMock();
    setUpTshirtRepositoryMock();
    setUpProcessingFeeMock();
    setUpTaxRepositoryMock();
    setUpInvoiceRepositoryMock();

    service = new GameServiceLayer(
            gameRepository, consoleRepository, tShirtRepository, taxRepository, processingFeeRepository, invoiceRepository
    );

    }
     @Test
     public void shouldCreateFindGame(){
         GameViewModel newGame1 = new GameViewModel();
         newGame1.setTitle("PlayStation");
         newGame1.setEsrbRating("E10+");
         newGame1.setDescription("New Game");
         newGame1.setPrice(new BigDecimal("20.08"));
         newGame1.setStudio("Sony");
         newGame1.setQuantity(2);

         newGame1 = service.createGame(newGame1);
         GameViewModel foundGameViewModel = service.getGame(10L);
         assertEquals(newGame1,foundGameViewModel);
    }

    @Test
    public void shouldUpdateGame(){

    }
    private void setUpInvoiceRepositoryMock() {
        invoiceRepository = mock(InvoiceRepository.class);

        Invoice invoice = new Invoice();
        invoice.setName("John Jake");
        invoice.setStreet("street");
        invoice.setCity("Charlotte");
        invoice.setState("NC");
        invoice.setZipcode("83749");
        invoice.setItemType("T-Shirt");
        invoice.setItemId(54);
        invoice.setUnitPrice(new BigDecimal("19.99"));
        invoice.setQuantity(2);
        invoice.setSubtotal(new BigDecimal("39.98"));
        invoice.setTax(new BigDecimal("2"));
        invoice.setProcessingFee(new BigDecimal("1.98"));
        invoice.setTotal(new BigDecimal("43.96"));

        Invoice invoice1 = new Invoice();
        invoice1.setId(20);
        invoice1.setName("John Jake");
        invoice1.setStreet("street");
        invoice1.setCity("Charlotte");
        invoice1.setState("NC");
        invoice1.setZipcode("83749");
        invoice1.setItemType("T-Shirt");
        invoice1.setItemId(54);
        invoice1.setUnitPrice(new BigDecimal("19.99"));
        invoice1.setQuantity(2);
        invoice1.setSubtotal(new BigDecimal("39.98"));
        invoice1.setTax(new BigDecimal("2"));
        invoice1.setProcessingFee(new BigDecimal("1.98"));
        invoice1.setTotal(new BigDecimal("43.96"));

        doReturn(invoice1).when(invoiceRepository).save(invoice);
        doReturn(Optional.of(invoice1)).when(invoiceRepository).findById(20L);

        //Get All...
        Invoice savedInvoice1 = new Invoice();
        savedInvoice1.setName("Sandy Beach");
        savedInvoice1.setStreet("123 Main St");
        savedInvoice1.setCity("any City");
        savedInvoice1.setState("NY");
        savedInvoice1.setZipcode("10016");
        savedInvoice1.setItemType("T-Shirt");
        savedInvoice1.setItemId(12);//pretending item exists with this id...
        savedInvoice1.setUnitPrice(new BigDecimal("12.50"));//pretending item exists with this price...
        savedInvoice1.setQuantity(2);
        savedInvoice1.setSubtotal(savedInvoice1.getUnitPrice().multiply(new BigDecimal(savedInvoice1.getQuantity())));
        savedInvoice1.setTax(savedInvoice1.getSubtotal().multiply(new BigDecimal("0.06")));
        savedInvoice1.setProcessingFee(new BigDecimal("10.00"));
        savedInvoice1.setTotal(savedInvoice1.getSubtotal().add(savedInvoice1.getTax()).add(savedInvoice1.getProcessingFee()));
        savedInvoice1.setId(22);

        Invoice savedInvoice2 = new Invoice();
        savedInvoice2.setName("Rob Bank");
        savedInvoice2.setStreet("888 Main St");
        savedInvoice2.setCity("any town");
        savedInvoice2.setState("NJ");
        savedInvoice2.setZipcode("08234");
        savedInvoice2.setItemType("Console");
        savedInvoice2.setItemId(120);//pretending item exists with this id...
        savedInvoice2.setUnitPrice(new BigDecimal("129.50"));//pretending item exists with this price...
        savedInvoice2.setQuantity(1);
        savedInvoice2.setSubtotal(savedInvoice2.getUnitPrice().multiply(new BigDecimal(savedInvoice2.getQuantity())));
        savedInvoice2.setTax(savedInvoice2.getSubtotal().multiply(new BigDecimal("0.08")));
        savedInvoice2.setProcessingFee(new BigDecimal("10.00"));
        savedInvoice2.setTotal(savedInvoice2.getSubtotal().add(savedInvoice2.getTax()).add(savedInvoice2.getProcessingFee()));
        savedInvoice2.setId(12);

        Invoice savedInvoice3 = new Invoice();
        savedInvoice3.setName("Sandy Beach");
        savedInvoice3.setStreet("123 Broad St");
        savedInvoice3.setCity("any where");
        savedInvoice3.setState("CA");
        savedInvoice3.setZipcode("90016");
        savedInvoice3.setItemType("Game");
        savedInvoice3.setItemId(19);//pretending item exists with this id...
        savedInvoice3.setUnitPrice(new BigDecimal("12.50"));//pretending item exists with this price...
        savedInvoice3.setQuantity(4);
        savedInvoice3.setSubtotal(savedInvoice3.getUnitPrice().multiply(new BigDecimal(savedInvoice3.getQuantity())));
        savedInvoice3.setTax(savedInvoice3.getSubtotal().multiply(new BigDecimal("0.09")));
        savedInvoice3.setProcessingFee(BigDecimal.ZERO);
        savedInvoice3.setTotal(savedInvoice3.getSubtotal().add(savedInvoice3.getTax()).add(savedInvoice3.getProcessingFee()));
        savedInvoice3.setId(73);

        List<Invoice> allList = new ArrayList<>();
        allList.add(savedInvoice1);
        allList.add(savedInvoice2);
        allList.add(savedInvoice3);

        doReturn(allList).when(invoiceRepository).findAll();
    }

    private void setUpTaxRepositoryMock() {
        taxRepository = mock(TaxRepository.class);

        Tax taxNC = new Tax();
        taxNC.setRate(new BigDecimal(".05"));
        taxNC.setState("NC");

        Tax taxNY = new Tax();
        taxNY.setRate(BigDecimal.ZERO);
        taxNY.setState("NY");

        doReturn(Optional.of(taxNC)).when(taxRepository).findById("NC");
        doReturn(Optional.of(taxNY)).when(taxRepository).findById("NY");
    }

    private void setUpProcessingFeeMock() {

            processingFeeRepository = mock(ProcessingFeeRepository.class);

            ProcessingFee processingFee = new ProcessingFee();
            processingFee.setFee(new BigDecimal("1.98"));
            processingFee.setProductType("T-Shirt");

            doReturn(Optional.of(processingFee)).when(processingFeeRepository).findById("T-Shirt");
    }

    private void setUpTshirtRepositoryMock() {
        tShirtRepository = mock(TShirtRepository.class);

        List<Tshirt> tshirtsByColor = new ArrayList<>();
        List<Tshirt> tshirtsBySize = new ArrayList<>();
        List<Tshirt> allTShirts = new ArrayList<>();

        Tshirt newTShirt1 = new Tshirt();
        newTShirt1.setSize("Medium");
        newTShirt1.setColor("Blue");
        newTShirt1.setDescription("V-Neck");
        newTShirt1.setPrice(new BigDecimal("19.99"));
        newTShirt1.setQuantity(5);

        Tshirt savedTShirt1 = new Tshirt();
        savedTShirt1.setId(54);
        savedTShirt1.setSize("Medium");
        savedTShirt1.setColor("Blue");
        savedTShirt1.setDescription("V-Neck");
        savedTShirt1.setPrice(new BigDecimal("19.99"));
        savedTShirt1.setQuantity(5);

        tshirtsByColor.add(savedTShirt1);
        tshirtsBySize.add(savedTShirt1);
        allTShirts.add(savedTShirt1);

        Tshirt newTShirt2 = new Tshirt();
        newTShirt2.setSize("Large");
        newTShirt2.setColor("Blue");
        newTShirt2.setDescription("long sleeve");
        newTShirt2.setPrice(new BigDecimal("30.99"));
        newTShirt2.setQuantity(8);

        Tshirt savedTShirt2 = new Tshirt();
        savedTShirt2.setId(60);
        savedTShirt2.setSize("Large");
        savedTShirt2.setColor("Blue");
        savedTShirt2.setDescription("long sleeve");
        savedTShirt2.setPrice(new BigDecimal("30.99"));
        savedTShirt2.setQuantity(8);

        allTShirts.add(savedTShirt2);
        tshirtsByColor.add(savedTShirt2);

        Tshirt newTShirt3 = new Tshirt();
        newTShirt3.setSize("Medium");
        newTShirt3.setColor("orange");
        newTShirt3.setDescription("sleeveless");
        newTShirt3.setPrice(new BigDecimal("15.99"));
        newTShirt3.setQuantity(3);

        Tshirt savedTShirt3 = new Tshirt();
        savedTShirt3.setId(72);
        savedTShirt3.setSize("Medium");
        savedTShirt3.setColor("orange");
        savedTShirt3.setDescription("sleeveless");
        savedTShirt3.setPrice(new BigDecimal("15.99"));
        savedTShirt3.setQuantity(3);

        allTShirts.add(savedTShirt3);
        tshirtsBySize.add(savedTShirt3);

        Tshirt newTShirt4 = new Tshirt();
        newTShirt4.setSize("Small");
        newTShirt4.setColor("Red");
        newTShirt4.setDescription("sleeveless");
        newTShirt4.setPrice(new BigDecimal("400"));
        newTShirt4.setQuantity(30);

        Tshirt savedTShirt4 = new Tshirt();
        savedTShirt4.setId(99);
        savedTShirt4.setSize("Small");
        savedTShirt4.setColor("Red");
        savedTShirt4.setDescription("sleeveless");
        savedTShirt4.setPrice(new BigDecimal("400"));
        savedTShirt4.setQuantity(30);

        doReturn(savedTShirt1).when(tShirtRepository).save(newTShirt1);
        doReturn(savedTShirt2).when(tShirtRepository).save(newTShirt2);
        doReturn(savedTShirt3).when(tShirtRepository).save(newTShirt3);
        doReturn(Optional.of(savedTShirt3)).when(tShirtRepository).findById(72L);
        doReturn(Optional.of(savedTShirt1)).when(tShirtRepository).findById(54L);
        doReturn(Optional.of(savedTShirt4)).when(tShirtRepository).findById(99L);

        doReturn(tshirtsByColor).when(tShirtRepository).findAllByColor("Blue");
        doReturn(tshirtsBySize).when(tShirtRepository).findAllBySize("Medium");
        doReturn(allTShirts).when(tShirtRepository).findAll();

    }

    private void setUpGameRepositoryMock() {
        gameRepository = mock(GameRepository.class);

        List<Game> allGames = new ArrayList<>();
        List<Game> gamesByStudio= new ArrayList<>();
        List<Game> gamesByTitle = new ArrayList<>();
        List<Game> gameByEsrbRating = new ArrayList<>();

        Game newGame1 = new Game();
        newGame1.setTitle("PlayStation");
        newGame1.setEsrbRating("E10+");
        newGame1.setDescription("New Game");
        newGame1.setPrice(new BigDecimal("20.08"));
        newGame1.setStudio("Sony");
        newGame1.setQuantity(2);

        Game savedGame1 = new Game();
        savedGame1.setId(10L);
        savedGame1.setTitle("PlayStation");
        savedGame1.setEsrbRating("E10+");
        savedGame1.setDescription("New Game");
        savedGame1.setPrice(new BigDecimal("20.08"));
        savedGame1.setStudio("Sony");
        savedGame1.setQuantity(2);

        gameByEsrbRating.add(savedGame1);
        allGames.add(savedGame1);

        Game newGame2 = new Game();
        newGame2.setTitle("FIFA2020");
        newGame2.setEsrbRating("M");
        newGame2.setDescription("New Game2");
        newGame2.setPrice(new BigDecimal("20.08"));
        newGame2.setStudio("Sony");
        newGame2.setQuantity(2);

        Game savedGame2 = new Game();
        savedGame2.setId(5L);
        savedGame2.setTitle("FIFA2020");
        savedGame2.setEsrbRating("M");
        savedGame2.setDescription("New Game2");
        savedGame2.setPrice(new BigDecimal("20.08"));
        savedGame2.setStudio("Sony");
        savedGame2.setQuantity(2);

        gameByEsrbRating.add(savedGame2);
        allGames.add(savedGame2);

        Game newGame3 = new Game();
        newGame3.setTitle("Fort Lines");
        newGame3.setEsrbRating("M");
        newGame3.setDescription("Zombie shooter game");
        newGame3.setPrice(new BigDecimal("37.99"));
        newGame3.setStudio("Dolby Studios");
        newGame3.setQuantity(3);

        Game savedGame3 = new Game();
        savedGame3.setId(60L);
        savedGame3.setTitle("Fort Lines");
        savedGame3.setEsrbRating("M");
        savedGame3.setDescription("Zombie shooter game");
        savedGame3.setPrice(new BigDecimal("37.99"));
        savedGame3.setStudio("Dolby Studios");
        savedGame3.setQuantity(3);
        gamesByTitle.add(savedGame3);
        gamesByStudio.add(savedGame3);
        allGames.add(savedGame3);

        doReturn(savedGame1).when(gameRepository).save(newGame1);
        doReturn(Optional.of(savedGame3)).when(gameRepository).findById(60L);
        doReturn(Optional.of(savedGame2)).when(gameRepository).findById(5L);
        doReturn(Optional.of(savedGame1)).when(gameRepository).findById(10L);
        doReturn(savedGame1).when(gameRepository).save(newGame1);
        doReturn(savedGame2).when(gameRepository).save(newGame2);

        doReturn(gameByEsrbRating).when(gameRepository).findAllByEsrbRating("M");
        doReturn(gamesByStudio).when(gameRepository).findAllByStudio("Sony");
        doReturn(gamesByTitle).when(gameRepository).findAllByTitle("FortLines");
    }

    private void setUpConsoleRepositoryMock() {
        consoleRepository = mock(ConsoleRepository.class);
        List<Console> allConsole = new ArrayList<>();
        List<Console> consoleByManufacturer = new ArrayList<>();

        Console newConsole1 = new Console();
        newConsole1.setModel("Playstation");
        newConsole1.setManufacturer("Sony");
        newConsole1.setMemoryAmount("120GB");
        newConsole1.setProcessor("Intel 17-9750H");
        newConsole1.setPrice(new BigDecimal("299.99"));
        newConsole1.setQuantity(4);

        Console savedConsole1 = new Console();
        savedConsole1.setId(40L);
        savedConsole1.setModel("Playstation");
        savedConsole1.setManufacturer("Sony");
        savedConsole1.setMemoryAmount("120GB");
        savedConsole1.setProcessor("Intel 17-9750H");
        savedConsole1.setPrice(new BigDecimal("299.99"));
        savedConsole1.setQuantity(4);

        consoleByManufacturer.add(savedConsole1);
        allConsole.add(savedConsole1);

        Console newConsole2 = new Console();
        newConsole2.setModel("FIFA2020");
        newConsole2.setManufacturer("Amazon");
        newConsole2.setMemoryAmount("120GB");
        newConsole2.setProcessor("Intel 17-9750H");
        newConsole2.setPrice(new BigDecimal("299.99"));
        newConsole2.setQuantity(2);

        Console savedConsole2 = new Console();
        savedConsole2.setId(20L);
        savedConsole2.setModel("FIFA2020");
        savedConsole2.setManufacturer("Amazon");
        savedConsole2.setMemoryAmount("120GB");
        savedConsole2.setProcessor("Intel 17-9750H");
        savedConsole2.setPrice(new BigDecimal("299.99"));
        savedConsole2.setQuantity(2);

        consoleByManufacturer.add(savedConsole2);
        allConsole.add(savedConsole2);

        Console newConsole3 = new Console();
        newConsole3.setModel("PS III");
        newConsole3.setManufacturer("Sony");
        newConsole3.setMemoryAmount("512Gb");
        newConsole3.setProcessor("AMD I7-9750A");
        newConsole3.setPrice(new BigDecimal("199.99"));
        newConsole3.setQuantity(40);

        Console savedConsole3 = new Console();
        savedConsole3.setId(38L);
        savedConsole3.setModel("PS III");
        savedConsole3.setManufacturer("Sony");
        savedConsole3.setMemoryAmount("512Gb");
        savedConsole3.setProcessor("AMD I7-9750A");
        savedConsole3.setPrice(new BigDecimal("199.99"));
        savedConsole3.setQuantity(40);

        allConsole.add(savedConsole3);

        doReturn(savedConsole1).when(consoleRepository).save(newConsole1);
        doReturn(savedConsole2).when(consoleRepository).save(newConsole2);
        doReturn(savedConsole3).when(consoleRepository).save(newConsole3);

        doReturn(Optional.of(savedConsole1)).when(consoleRepository).findById(40L);
        doReturn(consoleByManufacturer).when(consoleRepository).findAllByManufacturer("Sony");
        doReturn(allConsole).when(consoleRepository).findAll();
    }
}