package lk.kavi.nibm.pizzacreed.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import lk.kavi.nibm.pizzacreed.entity.BasketItem;

public interface BasketItemRepo extends JpaRepository<BasketItem, Integer> {

    @Query(
        value = "SELECT " +
                "bp.customer_id AS customerId, " +  
                "p.pizza_id AS pizzaId, " +
                "p.pizza_name AS pizzaName, " +
                "p.pizza_img AS pizzaImg, " +
                "bp.pizza_size_id AS pizzaSizeId, " +
                "s.size_name AS sizeName, " +
                "ps.pizza_price AS price, " +
                "bp.crust " +
        "FROM pizza p " +
        "INNER JOIN basket_pizza bp ON p.pizza_id = bp.pizza_id " +
        "INNER JOIN size s ON s.size_id = bp.pizza_size_id " +
        "INNER JOIN pizza_size ps ON ps.pizza_id = bp.pizza_id AND ps.size_id = bp.pizza_size_id " +
        "WHERE bp.customer_id = :customerId",
        nativeQuery = true
    )
    List<Object[]> findPizzaDetailsByCustomerId(@Param("customerId") int customerId);

    Optional<BasketItem> findByCustomerIdAndPizzaId(int customerId, int pizzaId);
}
