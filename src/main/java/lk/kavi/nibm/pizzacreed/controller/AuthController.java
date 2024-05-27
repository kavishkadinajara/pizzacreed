package lk.kavi.nibm.pizzacreed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lk.kavi.nibm.pizzacreed.dto.AuthDTO;
import lk.kavi.nibm.pizzacreed.dto.CustomerDTO;
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
    private ResponseDTO responseDTO;

    // ADMIN LOGIN
    @PostMapping("/auth/adminlogin")
    public ResponseEntity<ResponseDTO> adminLogin(@RequestBody AuthDTO authDTO) {
        try {
            String res = authService.adminLogin(authDTO);

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

    // CUSTOMER LOGIN
    @PostMapping("/auth/login")
    public ResponseEntity<ResponseDTO> customerLogin(@RequestBody CustomerDTO customerDTO) {
        try {
            String res = authService.customerLogin(customerDTO);

            if (VarList.RSP_SUCCESS.equals(res)) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Login successful");
                responseDTO.setContent(customerDTO);
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

    // REGISTER CUSTOMER
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @PostMapping("/auth/register")
    public ResponseEntity registerCustomer(@RequestBody CustomerDTO customerDTO, HttpStatus status) {
        try {
            String res = authService.registerCustomer(customerDTO);

            if(res.equals("00")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(customerDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

            } else if (res.equals("06")) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Employee already exists.");
                responseDTO.setContent(customerDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

            } else if(res.equals("10")) {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Empty Feilds");
                responseDTO.setContent(customerDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("error");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
