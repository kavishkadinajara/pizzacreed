package lk.kavi.nibm.pizzacreed.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShoppingBasketDTO {
    private int shoppingBasketId;
    private double totalAmount;
    private int customerId;
}
