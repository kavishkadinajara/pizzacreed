package lk.kavi.nibm.pizzacreed.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lk.kavi.nibm.pizzacreed.dto.BasketItemDTO;
import lk.kavi.nibm.pizzacreed.dto.PizzaDetailsDTO;
import lk.kavi.nibm.pizzacreed.dto.ResponseDTO;
import lk.kavi.nibm.pizzacreed.dto.BasketDTO;
import lk.kavi.nibm.pizzacreed.repo.BasketItemRepo;
import lk.kavi.nibm.pizzacreed.util.VarList;
import lk.kavi.nibm.pizzacreed.entity.BasketItem;

@Service
@Transactional
public class BasketItemService {

    @Autowired
    private BasketItemRepo basketItemRepo;

    @Autowired
    private ResponseDTO responseDTO;

    @Autowired
    private ModelMapper modelMapper;

    // ADD PIZZA TO BASKET
    public String addPizzaToBasket(BasketItemDTO basketItemDTO) {
        try {
            BasketItem basketItem = modelMapper.map(basketItemDTO, BasketItem.class);
            basketItemRepo.save(basketItem);
            return VarList.RSP_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return VarList.RSP_ERROR;
        }
    }

    // DELETE ITEM IN BASKET
    public String deleteItemInBasket(int customerId, int pizzaId) {
        try {
            Optional<BasketItem> optionalBasketItem = basketItemRepo.findByCustomerIdAndPizzaId(customerId, pizzaId);
            if (optionalBasketItem.isEmpty()) {
                return VarList.RSP_NO_DATA_FOUND;
            }
            basketItemRepo.delete(optionalBasketItem.get());
            return VarList.RSP_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return VarList.RSP_ERROR;
        }
    }

    // GET BASKET PIZZAS BY CUSTOMER ID
    public List<BasketDTO> getBasketPizzasByCustomerId(int customerId) {
        List<Object[]> basketItems = basketItemRepo.findPizzaDetailsByCustomerId(customerId);
        List<BasketDTO> basketDTOList = new ArrayList<>();

        try {
            basketItems.forEach(record -> {
                try {
                    BasketDTO basketDTO = new BasketDTO();
                    basketDTO.setCustomerId((Integer) record[0]);
                    basketDTO.setPizzaId((Integer) record[1]);
                    basketDTO.setPizzaName((String) record[2]);
                    basketDTO.setPizzaImg((String) record[3]);
                    basketDTO.setPizzaSizeId((Integer) record[4]);
                    basketDTO.setSizeName((String) record[5]);
                    basketDTO.setPrice((Double) record[6]);
                    basketDTO.setCrust((String) record[7]);

                    basketDTOList.add(basketDTO);
                } catch (IndexOutOfBoundsException e) {
                    System.err.println("Index out of bounds for record: " + java.util.Arrays.toString(record));
                } catch (Exception e) {
                    System.err.println("Error processing record: " + e + java.util.Arrays.toString(record));
                }
            });

            return basketDTOList;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
