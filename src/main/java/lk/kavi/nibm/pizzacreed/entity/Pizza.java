package lk.kavi.nibm.pizzacreed.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lk.kavi.nibm.pizzacreed.dto.PizzaDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "PIZZA")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pizzaId;
    private String pizzaName;
    private String pizzaDiscription; 
    private String categoryId; 
    private String pizzaImg;
    public void addAttribute(String string, List<PizzaDetailsDTO> pizzaDTOList) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addAttribute'");
    }

}
