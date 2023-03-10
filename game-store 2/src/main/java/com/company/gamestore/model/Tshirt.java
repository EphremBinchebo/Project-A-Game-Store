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
@Table(name="t_shirt")
public class Tshirt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_shirt_id")
    private long id;
    private String size;
    private String color;
    private String description;
    private BigDecimal price;
    private long quantity;
}
