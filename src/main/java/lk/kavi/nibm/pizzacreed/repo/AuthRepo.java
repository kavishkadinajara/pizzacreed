package lk.kavi.nibm.pizzacreed.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import lk.kavi.nibm.pizzacreed.dto.AuthDTO;
import lk.kavi.nibm.pizzacreed.entity.Auth;

public interface AuthRepo extends JpaRepository<Auth, Integer> {
    // Method to find an Auth entity by username
    Auth findByUsername(AuthDTO userDTO);
}
