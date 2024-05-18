package lk.kavi.nibm.pizzacreed.entity;

import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;  // Add this import
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
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_id") 
    private int userId;

    @Column(name = "username")  
    private String username;

    @Column(name = "password") 
    private String password;
}
