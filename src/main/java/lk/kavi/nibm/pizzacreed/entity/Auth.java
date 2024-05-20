package lk.kavi.nibm.pizzacreed.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "AUTH")
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authId;
    private String username;
    private String password;
}
