package lk.kavi.nibm.pizzacreed.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PizzaSizeDTO {
    private int sizeId;
    private String sizeName; // Add this field
    private double price;
}
