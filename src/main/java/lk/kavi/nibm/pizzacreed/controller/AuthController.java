package lk.kavi.nibm.pizzacreed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lk.kavi.nibm.pizzacreed.dto.AuthDTO;
import lk.kavi.nibm.pizzacreed.dto.PizzaDTO; // Assume you have this DTO for Pizza
import lk.kavi.nibm.pizzacreed.dto.ResponseDTO;
import lk.kavi.nibm.pizzacreed.service.AuthService;
import lk.kavi.nibm.pizzacreed.service.PizzaService; // Assume you have this service for Pizza
import lk.kavi.nibm.pizzacreed.util.VarList;

@RestController
@CrossOrigin
@RequestMapping("api/pizzacreed")
public class AuthController {

    @Autowired 
    private AuthService authService;

    @Autowired
    private PizzaService pizzaService; // Assuming this service exists

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/auth/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody AuthDTO authDTO) {
        try {
            String res = authService.login(authDTO);

            if (VarList.RSP_SUCCESS.equals(res)) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Login successful");
                responseDTO.setContent(authDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Invalid credentials");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/pizza/add")
    public ResponseEntity<ResponseDTO> addPizza(@RequestBody PizzaDTO pizzaDTO) {
        try {
            pizzaService.addPizza(pizzaDTO);
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Pizza added successfully");
            responseDTO.setContent(pizzaDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
