package lk.kavi.nibm.pizzacreed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import lk.kavi.nibm.pizzacreed.dto.BasketItemDTO;
import lk.kavi.nibm.pizzacreed.dto.ResponseDTO;
import lk.kavi.nibm.pizzacreed.dto.BasketDTO;
import lk.kavi.nibm.pizzacreed.service.BasketItemService;
import lk.kavi.nibm.pizzacreed.util.VarList;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/pizzacreed/basketitem")
public class BasketItemController {

    @Autowired
    private ResponseDTO responseDTO;

    @Autowired
    private BasketItemService basketItemService;

    // ADD PIZZA TO BASKET
    @PostMapping("/addpizza")
    public ResponseEntity<ResponseDTO> addPizzaToBasket(@RequestBody BasketItemDTO basketItemDTO) {
        try {
            String res = basketItemService.addPizzaToBasket(basketItemDTO);

            if (VarList.RSP_SUCCESS.equals(res)) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully added");
                responseDTO.setContent(basketItemDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else if (VarList.RSP_DUPLICATED.equals(res)) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Pizza already exists.");
                responseDTO.setContent(basketItemDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            } else {
                responseDTO.setCode(VarList.RSP_ERROR);
                responseDTO.setMessage("Error");
                responseDTO.setContent(basketItemDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // REMOVE PIZZA FROM BASKET
    @DeleteMapping("/{customerId}/pizza/{pizzaId}")
    public ResponseEntity<ResponseDTO> deleteItemInBasket(
            @PathVariable("customerId") int customerId, 
            @PathVariable("pizzaId") int pizzaId) {
        try {
            String res = basketItemService.deleteItemInBasket(customerId, pizzaId);

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

    @GetMapping("/{customerId}")
    public ResponseEntity<ResponseDTO> getBasketPizzasByCustomerId(@PathVariable("customerId") int customerId) {
        try {
            List<BasketDTO> basketDTOList = basketItemService.getBasketPizzasByCustomerId(customerId);
            if (basketDTOList.isEmpty()) {
                responseDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDTO.setMessage("No pizza found in your order");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
            } 

            responseDTO.setCode(VarList.RSP_SUCCESS);
            responseDTO.setMessage("Success");
            responseDTO.setContent(basketDTOList);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage("Error: " + e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
