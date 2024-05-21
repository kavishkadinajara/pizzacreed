package lk.kavi.nibm.pizzacreed.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lk.kavi.nibm.pizzacreed.dto.PizzaDetailsDTO;
import lk.kavi.nibm.pizzacreed.entity.Pizza;

public interface PizzaRepo extends JpaRepository<Pizza, Integer> {

    boolean existsByPizzaName(String pizzaName);

    @Query(value = "SELECT p.pizza_id AS pizzaId, p.pizza_name AS pizzaName, p.pizza_discription AS pizzaDiscription, " +
                   "p.pizza_img AS pizzaImg, ps.size_id AS sizeId, ps.pizza_price AS price " +
                   "FROM pizza p " +
                   "LEFT JOIN pizza_size ps ON p.pizza_id = ps.pizza_id", nativeQuery = true)
    List<Object[]> getAllPizzaDetails();
}
