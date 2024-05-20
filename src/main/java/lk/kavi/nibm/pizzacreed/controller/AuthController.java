package lk.kavi.nibm.pizzacreed.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lk.kavi.nibm.pizzacreed.dto.AuthDTO;
import lk.kavi.nibm.pizzacreed.dto.ResponseDTO;
import lk.kavi.nibm.pizzacreed.service.AuthService;
import lk.kavi.nibm.pizzacreed.util.VarList;

@RestController
@CrossOrigin
@RequestMapping("api/pizzacreed/auth")
public class AuthController {

    @Autowired 
    private AuthService authService;

    @Autowired
    private ResponseDTO responseDTO;

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody AuthDTO authDTO) {
        try {
            String res = authService.login(authDTO);
            System.out.println("Login response code: " + res);
            System.out.println("AuthDTO: " + authDTO);

            if (VarList.RSP_SUCCESS.equals(res)) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Login successful");
                responseDTO.setContent(authDTO);
            } else if (VarList.RSP_FAIL.equals(res)) {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Invalid credentials");
                responseDTO.setContent(null);
            } else if ("02".equals(res)) {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Account locked");
                responseDTO.setContent(null);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Login error");
                responseDTO.setContent(null);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
        }

        // Print the response content
        System.out.println("ResponseDTO: " + responseDTO);

        HttpStatus status = (VarList.RSP_SUCCESS.equals(responseDTO.getCode())) ? HttpStatus.ACCEPTED : HttpStatus.UNAUTHORIZED;
        return new ResponseEntity<>(responseDTO, status);
    }

    // GET ALL USERS FROM TABLE
    @GetMapping("/findAllUsers")
    public ResponseEntity<ResponseDTO> findAllEmployees() {
        try {
            List<AuthDTO> employeeDTOList = authService.getAll();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Success");
            responseDTO.setContent(employeeDTOList);
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
        }

        // Print the response content
        System.out.println("ResponseDTO: " + responseDTO);

        HttpStatus status = (VarList.RSP_SUCCESS.equals(responseDTO.getCode())) ? HttpStatus.ACCEPTED : HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(responseDTO, status);
    }
}
