package lk.kavi.nibm.pizzacreed.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lk.kavi.nibm.pizzacreed.dto.PizzaDetailsDTO;
import lk.kavi.nibm.pizzacreed.entity.Pizza;

public interface PizzaRepo extends JpaRepository<Pizza, Integer> {

    boolean existsByPizzaName(String pizzaName);

    @Query(value = "SELECT p.pizza_id AS pizzaId, " +
               "p.pizza_name AS pizzaName, " +
               "p.pizza_discription AS pizzaDiscription, " +
               "p.pizza_img AS pizzaImg, " +
               "s.size_id AS sizeId, " +
               "s.size_name AS sizeName, " +
               "ps.pizza_price AS pizzaPrice " +
               "FROM pizza p " +
               "INNER JOIN pizza_size ps ON p.pizza_id = ps.pizza_id " +
               "INNER JOIN size s ON ps.size_id = s.size_id", nativeQuery = true)
List<Object[]> getAllPizzaDetails();

}
