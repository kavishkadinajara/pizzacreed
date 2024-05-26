package lk.kavi.nibm.pizzacreed.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDTO {

    private int customerId;
    private String customerName;
    private String customerEmail;
    private String customerTele;
    private String password;
    private String customerAddress;

}
