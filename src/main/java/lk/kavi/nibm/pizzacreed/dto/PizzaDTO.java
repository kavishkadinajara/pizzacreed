package lk.kavi.nibm.pizzacreed.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PizzaDTO {

    private int pizzaId;
    private String pizzaName;
    private String pizzaDiscription; 
    private String catogeryId; 
    private String pizzaImg;
}
