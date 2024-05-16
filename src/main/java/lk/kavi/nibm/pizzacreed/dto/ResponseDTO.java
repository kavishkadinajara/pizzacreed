package lk.kavi.nibm.pizzacreed.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class ResponseDTO {
    
    private String code;
    private String message;
    private Object content;
}
