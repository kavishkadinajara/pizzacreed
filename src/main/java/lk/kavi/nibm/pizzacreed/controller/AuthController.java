package lk.kavi.nibm.pizzacreed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lk.kavi.nibm.pizzacreed.service.AuthService;
import lk.kavi.nibm.pizzacreed.util.VarList;
import lk.kavi.nibm.pizzacreed.dto.AuthDTO;
import lk.kavi.nibm.pizzacreed.dto.ResponseDTO;

@RestController
@CrossOrigin
@RequestMapping("api/auth/pizzacreed/")
public class AuthController {

    @Autowired 
    private AuthService authService;
    @Autowired
    private ResponseDTO  responseDTO;

    // LOGIN
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @PostMapping("/adminlogin")
    public ResponseEntity login(@RequestBody AuthDTO authDTO) {
        try {
            String res = authService.login(authDTO);

            if (res.equals("00")) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Login successful");
                responseDTO.setContent(authDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

            } else if (res.equals("01")) {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Invalid credentials");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.UNAUTHORIZED);

            } else if (res.equals("02")) {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Account locked");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.FORBIDDEN);

            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Login error");
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
