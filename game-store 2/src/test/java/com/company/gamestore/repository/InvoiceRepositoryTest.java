package com.company.gamestore.repository;

import com.company.gamestore.model.Invoice;
import com.company.gamestore.model.ProcessingFee;
import com.company.gamestore.model.Tax;
import com.company.gamestore.model.Tshirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceRepositoryTest {
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    GameRepository gameRepository;
    @Autowired
    ConsoleRepository consoleRepository;
    @Autowired
    TShirtRepository tShirtRepository;
    @Autowired
    ProcessingFeeRepository processingFeeRepository;
    @Autowired
    TaxRepository taxRepository;

    @Before
    public void setUp() throws Exception {
        invoiceRepository.deleteAll();
        gameRepository.deleteAll();
        consoleRepository.deleteAll();
        tShirtRepository.deleteAll();
        invoiceRepository.deleteAll();
        processingFeeRepository.deleteAll();

        ProcessingFee tshirtProcessingFee = new ProcessingFee();
        tshirtProcessingFee.setProductType("Tshirt");
        tshirtProcessingFee.setFee(new BigDecimal("1.98"));

        ProcessingFee gameProcessingFee = new ProcessingFee();
        gameProcessingFee.setProductType("Game");
        gameProcessingFee.setFee(new BigDecimal("1.49"));

        ProcessingFee consoleProcessingFee = new ProcessingFee();
        consoleProcessingFee.setProductType("Console");
        consoleProcessingFee.setFee(new BigDecimal("14.99"));

        processingFeeRepository.save(tshirtProcessingFee);
        processingFeeRepository.save(gameProcessingFee);
        processingFeeRepository.save(consoleProcessingFee);
    }

    @Test
    public void shouldAddFindDelete() {

        //Arrange
        Tshirt tshirt1 = new Tshirt();
        tshirt1.setSize("M");
        tshirt1.setColor("Blue");
        tshirt1.setDescription("V-neck");

        //tshirt1.setPrice(new BigDecimal("15.09").setScale(2, RoundingMode.HALF_UP));
        tshirt1.setPrice(new BigDecimal("15.99"));

        tshirt1.setQuantity(8);
        tshirt1 = tShirtRepository.save(tshirt1);

        Invoice invoice1 = new Invoice();
        invoice1.setName("Joe Black");
        invoice1.setStreet("123 Main St");
        invoice1.setCity("any City");
        invoice1.setState("NY");
        invoice1.setZipcode("10016");
        invoice1.setItemType("Tshirt");
        invoice1.setItemId(tshirt1.getId());
        invoice1.setUnitPrice(tshirt1.getPrice());
        invoice1.setQuantity(2);


        invoice1.setSubtotal(
                tshirt1.getPrice().multiply(
                        new BigDecimal(invoice1.getQuantity()))
        );
        Optional<Tax> tax = taxRepository.findById(invoice1.getState());
        assertTrue(tax.isPresent());
        invoice1.setTax(invoice1.getSubtotal().multiply(tax.get().getRate()));

        Optional<ProcessingFee> processingFee = processingFeeRepository.findById(invoice1.getItemType());
        assertTrue(processingFee.isPresent());
        invoice1.setProcessingFee(processingFee.get().getFee());


        invoice1.setTotal(invoice1.getSubtotal().add(invoice1.getTax()).add(invoice1.getProcessingFee()));

        //Act
        invoice1 = invoiceRepository.save(invoice1);
        Optional<Invoice> invoice2 = invoiceRepository.findById(invoice1.getId());

        //Assert
        assertTrue(invoice2.isPresent());
//        assertEquals(invoice1, invoice2.get());

        //Act
        invoiceRepository.deleteById(invoice1.getId());
        invoice2 = invoiceRepository.findById(invoice1.getId());

        //Assert
        assertFalse(invoice2.isPresent());
    }

    @Test
    public void shouldFindByName(){
        Tshirt tshirt1 = new Tshirt();
        tshirt1.setSize("M");
        tshirt1.setColor("Blue");
        tshirt1.setDescription("V-neck");

        //tshirt1.setPrice(new BigDecimal("15.09").setScale(2, RoundingMode.HALF_UP));
        tshirt1.setPrice(new BigDecimal("15.99"));

        tshirt1.setQuantity(8);
        tshirt1 = tShirtRepository.save(tshirt1);

        Invoice invoice1 = new Invoice();
        invoice1.setName("Joe Black");
        invoice1.setStreet("123 Main St");
        invoice1.setCity("any City");
        invoice1.setState("NY");
        invoice1.setZipcode("10016");
        invoice1.setItemType("Tshirt");
        invoice1.setItemId(tshirt1.getId());
        invoice1.setUnitPrice(tshirt1.getPrice());
        invoice1.setQuantity(2);


        invoice1.setSubtotal(
                tshirt1.getPrice().multiply(
                        new BigDecimal(invoice1.getQuantity()))
        );
        Optional<Tax> tax = taxRepository.findById(invoice1.getState());
        assertTrue(tax.isPresent());
        invoice1.setTax(invoice1.getSubtotal().multiply(tax.get().getRate()));

        Optional<ProcessingFee> processingFee = processingFeeRepository.findById(invoice1.getItemType());
        assertTrue(processingFee.isPresent());
        invoice1.setProcessingFee(processingFee.get().getFee());


        invoice1.setTotal(invoice1.getSubtotal().add(invoice1.getTax()).add(invoice1.getProcessingFee()));

        //Act
        invoice1 = invoiceRepository.save(invoice1);

        List<Invoice> foundInvoice = invoiceRepository.findByName(invoice1.getName());
        List<Invoice> foundNoInvoice = invoiceRepository.findByName("invalidname");

        //Assert
        assertEquals(foundInvoice.size(), 1);
        assertTrue(foundNoInvoice.isEmpty());
    }
}