package lk.kavi.nibm.pizzacreed.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "shopping_basket")
public class ShoppingBasket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shoppingBasketId;
    private double totalAmount;
    private int customerId;

}
