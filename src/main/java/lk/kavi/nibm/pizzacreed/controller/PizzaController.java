package lk.kavi.nibm.pizzacreed.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lk.kavi.nibm.pizzacreed.dto.PizzaDTO;
import lk.kavi.nibm.pizzacreed.dto.PizzaDetailsDTO;
import lk.kavi.nibm.pizzacreed.dto.ResponseDTO;
import lk.kavi.nibm.pizzacreed.service.PizzaService;
import lk.kavi.nibm.pizzacreed.util.VarList;

@RestController
@CrossOrigin
@RequestMapping("api/pizzacreed/pizza")
public class PizzaController {

    @Autowired 
    private PizzaService pizzaService;
    
    @Autowired
    private ResponseDTO responseDTO;

    // Predefined map of valid API keys
    private Map<String, String> validApiKeys = new HashMap<>() {{
        put("pizza-gallefort-9911", "1919gallepizzafort");
    }};

    // GET ALL Pizzas FROM TABLE
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @GetMapping("/menu")
    public ResponseEntity findAllPizza() {
        try {
            List<PizzaDetailsDTO> pizzaDTOList = pizzaService.getAllPizzas();
            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Success");
            responseDTO.setContent(pizzaDTOList);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
        } catch (IndexOutOfBoundsException e) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Index out of bounds: " + e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Error: " + e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    // GET Pizza BY ID
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @GetMapping("/menu/{pizzaId}")
    public ResponseEntity findPizzaById(@PathVariable int pizzaId) {
        try {
            PizzaDetailsDTO pizzaDTO = pizzaService.findPizzaById(pizzaId);
            if (pizzaDTO == null) {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No pizza found with ID: " + pizzaId);
                responseDTO.setContent(null);
                return new ResponseEntity(responseDTO, HttpStatus.NOT_FOUND);
            }

            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Success");
            responseDTO.setContent(pizzaDTO);
            return new ResponseEntity(responseDTO, HttpStatus.OK);
        } catch (IndexOutOfBoundsException e) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Index out of bounds: " + e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Error: " + e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // GET ALL Pizzas FROM TABLE 
    @SuppressWarnings({"rawtypes", "unchecked"})
    @GetMapping("/menu1")
    public ResponseEntity findPizza() {
        try {
            List<PizzaDTO> pizzaDTOList = pizzaService.getAllEmployees();
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
    public ResponseEntity addPizza(
        @RequestBody PizzaDTO pizzaDTO,
        @RequestHeader(value = "API-KEY-USERNAME", required = true) String apiKeyUsername
        //@RequestHeader(value = "API-KEY-PASSWORD", required = true) String apiKeyPassword
    ) {
       System.out.println("Received API-KEY-USERNAME: " + apiKeyUsername);
      // System.out.println("Received API-KEY-PASSWORD: " + apiKeyPassword);
      // System.out.println("Valid API Keys: " + validApiKeys + "\n");
 
        try {
            // Validate API Key
            if (apiKeyUsername == null ||
               !validApiKeys.containsKey(apiKeyUsername)) {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Unauthorized access");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.UNAUTHORIZED);
            }

            String res = pizzaService.addPizza(pizzaDTO);
            System.out.println(res);
            System.out.println(validApiKeys); 
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


    // UPDATE PIZZA
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @PutMapping("/updatepizza")
    public ResponseEntity updatePizza(
        
        @RequestBody PizzaDTO pizzaDTO,
        @RequestHeader(value = "API-KEY-USERNAME", required = true) String apiKeyUsername
        //@RequestHeader(value = "API-KEY-PASSWORD", required = true) String apiKeyPassword
    ) {
       System.out.println("Received API-KEY-USERNAME: " + apiKeyUsername);
      // System.out.println("Received API-KEY-PASSWORD: " + apiKeyPassword);
      // System.out.println("Valid API Keys: " + validApiKeys + "\n");
 
        try {
            // Validate API Key
            if (apiKeyUsername == null ||
               !validApiKeys.containsKey(apiKeyUsername)) {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Unauthorized access");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.UNAUTHORIZED);
            }

            String res = pizzaService.updatePizza(pizzaDTO);
            System.out.println(res);
            System.out.println(validApiKeys); 
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

    // DELETE PIZZA CONTROLLER METHOD
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @DeleteMapping("/deletePizza/{pizzaId}")
    public ResponseEntity<?> deletePizza(
        @PathVariable int pizzaId,
        @RequestHeader(value = "API-KEY-USERNAME", required = true) String apiKeyUsername
        //@RequestHeader(value = "API-KEY-PASSWORD", required = true) String apiKeyPassword
    ) {
        System.out.println("Received API-KEY-USERNAME: " + apiKeyUsername);
       // System.out.println("Received API-KEY-PASSWORD: " + apiKeyPassword);
        System.out.println("Valid API Keys: " + validApiKeys + "\n");
 
        try {
            // Validate API Key
            if (apiKeyUsername == null ||
                !validApiKeys.containsKey(apiKeyUsername)) {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Unauthorized access");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.UNAUTHORIZED);
            }
 
            String res = pizzaService.deletePizza(pizzaId);
 
            if (VarList.RSP_NO_DATA_FOUND.equals(res)) {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("Pizza not found");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
            } else if (VarList.RSP_SUCCESS.equals(res)) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage("Unknown error");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
