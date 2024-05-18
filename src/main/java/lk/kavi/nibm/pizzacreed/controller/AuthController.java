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
@RequestMapping("api/auth/pizzacreed")
public class AuthController {

    @Autowired 
    private AuthService authService;
    @Autowired
    private ResponseDTO  responseDTO;

    // // SAVE DATA IN DB
    // @SuppressWarnings({ "rawtypes", "unchecked" })
    // @PostMapping("/login")
    // public ResponseEntity login(@RequestBody AuthDTO authDTO) {
    //     System.out.println(authDTO);
    //     try {
    //         String res = authService.login(authDTO);

    //         if(res.equals("00")) {
    //             responseDTO.setCode(VarList.RSP_SUCCESS);
    //             responseDTO.setMessage("Success");
    //             responseDTO.setContent(authDTO);
    //             return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

    //         } else if (res.equals("06")) {
    //             responseDTO.setCode(VarList.RSP_DUPLICATED);
    //             responseDTO.setMessage("Employee already exists.");
    //             responseDTO.setContent(authDTO);
    //             return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

    //         } else if(res.equals("10")) {
    //             responseDTO.setCode(VarList.RSP_FAIL);
    //             responseDTO.setMessage("Empty Feilds");
    //             responseDTO.setContent(authDTO);
    //             return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

    //         } else {
    //             responseDTO.setCode(VarList.RSP_FAIL);
    //             responseDTO.setMessage("error");
    //             responseDTO.setContent(null);
    //             return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
    //         }

    //     } catch (Exception ex) {
    //         responseDTO.setCode(VarList.RSP_ERROR);
    //         responseDTO.setMessage(ex.getMessage());
    //         responseDTO.setContent(null);
    //         return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
        
    // }
    

    // GET ALL EMPLOYEES FROM TABLE
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @GetMapping("/findAllEmployees")
    public ResponseEntity findAllEmployees() throws Exception{

        try {
            List<AuthDTO> employeeDTOList = authService.getAll();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Success");
            responseDTO.setContent(employeeDTOList);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
    }
}
