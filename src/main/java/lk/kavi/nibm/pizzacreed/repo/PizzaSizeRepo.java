package lk.kavi.nibm.pizzacreed.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import lk.kavi.nibm.pizzacreed.entity.PizzaSize;

public interface PizzaSizeRepo extends JpaRepository<PizzaSize, Integer> {
    List<PizzaSize> findByPizzaId(int pizzaId);
}
