package lk.kavi.nibm.pizzacreed.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import lk.kavi.nibm.pizzacreed.entity.Customer;
import lk.kavi.nibm.pizzacreed.entity.ShoppingBasket;
@SuppressWarnings("unused")
public interface ShoppingBasketRepo extends JpaRepository<ShoppingBasket, Integer> {

}
