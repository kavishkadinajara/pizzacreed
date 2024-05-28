package lk.kavi.nibm.pizzacreed.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BasketDTO {
    private int customerId;  
    private int pizzaId;
    private String pizzaName;
    private String pizzaImg;
    private int pizzaSizeId;
    private String sizeName;
    private double price;
    private String crust;
}
