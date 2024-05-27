package lk.kavi.nibm.pizzacreed.repo;

import lk.kavi.nibm.pizzacreed.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{
    Customer findByCustomerEmail(String customerEmail);
}
