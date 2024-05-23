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
@Table(name = "pizza_size")
public class PizzaSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int pizzaSizeId;
    private int pizzaId;
    private int sizeId;
    private double pizzaPrice;
}
