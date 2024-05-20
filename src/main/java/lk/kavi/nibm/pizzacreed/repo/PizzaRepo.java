package lk.kavi.nibm.pizzacreed.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import lk.kavi.nibm.pizzacreed.dto.PizzaDTO;
import lk.kavi.nibm.pizzacreed.entity.Pizza;

public interface PizzaRepo extends JpaRepository<Pizza, Integer>{

    void save(PizzaDTO pizzaDTO);

    boolean existsByPizzaName(String pizzaName);

    
} 