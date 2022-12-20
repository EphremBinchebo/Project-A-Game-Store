package com.company.gamestore.viewModel;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
public class GameViewModel {
    private Long id;
    @NotEmpty(message = "Title of the game is required.")
    private String title;
    @NotEmpty(message = "ESRB Rating of the game is required.")
    private String esrbRating;
    @NotEmpty(message = "Description of the game is required.")
    private String description;

    @NotNull(message = "Price of the game is required.")
    @DecimalMin(value = "0.0", inclusive = true, message = "Min Price for this item is $0.0")
    @DecimalMax(value = "999.99", inclusive = true, message = "Max Price is $999.99")
    private BigDecimal price;
    @NotEmpty(message = "Studio of the game is required.")
    private String studio;
    @NotNull(message = "Game quantity is required")
    @Min(value = 1, message = "Min Quantity is 1")
    @Max(value = 50000, message = "Max Quantity is 50,000")
    private long quantity;
}

