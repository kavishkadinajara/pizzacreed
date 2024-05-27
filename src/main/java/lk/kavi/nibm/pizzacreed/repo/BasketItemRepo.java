package lk.kavi.nibm.pizzacreed.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import lk.kavi.nibm.pizzacreed.entity.BasketItem;

public interface BasketItemRepo extends JpaRepository<BasketItem, Integer> {
    Optional<BasketItem> findByCustomerIdAndPizzaId(int customerId, int pizzaId);
}
