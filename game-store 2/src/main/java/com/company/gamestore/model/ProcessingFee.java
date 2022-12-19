package com.company.gamestore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="processing_fee")
public class ProcessingFee {
    @Id
    @Column(name = "product_type")
    private String productType;
    private BigDecimal fee;
}
