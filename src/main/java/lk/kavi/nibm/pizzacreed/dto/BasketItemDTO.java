package lk.kavi.nibm.pizzacreed.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BasketItemDTO {
    private int basketPizzaId;
    private int pizzaSizeId;
    private int pizzaId;
    private int customerId;
    private String crust;
}
