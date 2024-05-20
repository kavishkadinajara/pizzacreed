package lk.kavi.nibm.pizzacreed.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lk.kavi.nibm.pizzacreed.repo.PizzaRepo;
import lk.kavi.nibm.pizzacreed.util.VarList;
import lk.kavi.nibm.pizzacreed.dto.PizzaDTO;
import lk.kavi.nibm.pizzacreed.entity.Pizza;

@Service
@Transactional
public class PizzaService {

    @Autowired
    private PizzaRepo pizzaRepo;

    @Autowired
    private ModelMapper modelMapper;

    // GET ALL PIZZAS FROM TABLE
    public List<PizzaDTO> getAllPizzas() {
        List<Pizza> pizzaList = pizzaRepo.findAll();
        return modelMapper.map(pizzaList, new TypeToken<ArrayList<PizzaDTO>>() {}.getType());
    }

    // ADD NEW PIZZA
    public String addPizza(PizzaDTO pizzaDTO) {
        try {
            if (pizzaDTO.getPizzaName() == null || pizzaDTO.getPizzaDiscription() == null || pizzaDTO.getPizzaImg() == null) {
                return VarList.RSP_FAIL;
            }

            boolean pizza = pizzaRepo.existsByPizzaName(pizzaDTO.getPizzaName());
            if (pizza != false) {
                return VarList.RSP_DUPLICATED;
            }

            Pizza newPizza = modelMapper.map(pizzaDTO, Pizza.class);
            pizzaRepo.save(newPizza);
            return VarList.RSP_SUCCESS;
        } catch (Exception e) {
            throw new RuntimeException("Error adding pizza", e);
        }
    }
}
