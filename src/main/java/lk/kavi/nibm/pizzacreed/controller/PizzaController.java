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

import lk.kavi.nibm.pizzacreed.dto.PizzaDTO;
import lk.kavi.nibm.pizzacreed.dto.ResponseDTO;
import lk.kavi.nibm.pizzacreed.service.PizzaService;
import lk.kavi.nibm.pizzacreed.util.VarList;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("api/pizzacreed/pizza")
public class PizzaController {

    @Autowired 
    private PizzaService pizzaService;
    
    @Autowired
    private ResponseDTO responseDTO;

    // GET ALL Pizza FROM TABLE
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @GetMapping("/menu")
    public ResponseEntity findAllPizza() throws Exception {
        try {
            List<PizzaDTO> pizzaDTOList = pizzaService.getAllPizzas();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Success");
            responseDTO.setContent(pizzaDTOList);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ADD NEW PIZZA
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @PostMapping("/addPizza")
    public ResponseEntity addPizza(@RequestBody PizzaDTO pizzaDTO, HttpServletRequest request) throws Exception {
        try {
            // Validate API Key
            String apiKey = (String) request.getSession().getAttribute("apiKey");
            System.out.println(apiKey);
            if (apiKey == null || !apiKey.equals("your_generated_api_key")) { // Use actual generated API key
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Unauthorized access");
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.UNAUTHORIZED);
            }

            String res = pizzaService.addPizza(pizzaDTO);
            if(res.equals(VarList.RSP_SUCCESS)) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully added");
                responseDTO.setContent(pizzaDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else if(res.equals(VarList.RSP_DUPLICATED)) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Pizza already exists.");
                responseDTO.setContent(pizzaDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            } else if(res.equals(VarList.RSP_FAIL)) {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Empty Fields");
                responseDTO.setContent(pizzaDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(pizzaDTO);
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
