package lk.kavi.nibm.pizzacreed.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PizzaDetailsDTO {
    private int pizzaId;
    private String pizzaName;
    private String pizzaDiscription;
    private String categoryId; // Corrected the spelling here
    private String pizzaImg;
    private List<PizzaSizeDTO> sizes; // Corrected the field type
}
