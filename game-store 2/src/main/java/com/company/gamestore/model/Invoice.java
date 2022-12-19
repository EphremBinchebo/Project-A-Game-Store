package com.company.gamestore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private long id;
    private String name;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String itemType;
    private long itemId;
    private BigDecimal unitPrice;
    private long quantity;
    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal processingFee;
    private BigDecimal total;
}
