package lk.kavi.nibm.pizzacreed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import lk.kavi.nibm.pizzacreed.dto.BasketItemDTO;
import lk.kavi.nibm.pizzacreed.dto.ResponseDTO;
import lk.kavi.nibm.pizzacreed.service.BasketItemService;
import lk.kavi.nibm.pizzacreed.util.VarList;

@RestController
@CrossOrigin
@RequestMapping("api/pizzacreed/basketitem")
public class BasketItemController {

    @Autowired
    private ResponseDTO responseDTO;

    @Autowired
    private BasketItemService basketItemService;

    // ADD PIZZA TO BASKET
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @PostMapping("/addpizza")
    public ResponseEntity addPizzaToBasket(@RequestBody BasketItemDTO basketItemDTO) {
        try {
            String res = basketItemService.addPizzaToBasket(basketItemDTO);

            if(res.equals(VarList.RSP_SUCCESS)) {
                responseDTO.setCode(VarList.RSP_SUCCESS);
                responseDTO.setMessage("Successfully added");
                responseDTO.setContent(basketItemDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            } else if(res.equals(VarList.RSP_DUPLICATED)) {
                responseDTO.setCode(VarList.RSP_DUPLICATED);
                responseDTO.setMessage("Pizza already exists.");
                responseDTO.setContent(basketItemDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            } else if(res.equals(VarList.RSP_FAIL)) {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Empty Fields");
                responseDTO.setContent(basketItemDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            } else {
                responseDTO.setCode(VarList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(basketItemDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            responseDTO.setCode(VarList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // REMOVE PIZZA FROM BASKET
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @DeleteMapping("/{shoppingbasketid}/pizza/{pizzaid}")
    public ResponseEntity deleteItemInBasket(
        @PathVariable("shoppingbasketid") int shoppingBasketId, 
        @PathVariable("pizzaid") int pizzaId
    ) {
        try {
            String res = basketItemService.deleteItemInBasket(shoppingBasketId, pizzaId);

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
