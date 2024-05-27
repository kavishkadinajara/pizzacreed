package lk.kavi.nibm.pizzacreed.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import lk.kavi.nibm.pizzacreed.dto.BasketItemDTO;
import lk.kavi.nibm.pizzacreed.dto.ResponseDTO;
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
            BasketItem basketItem = basketItemRepo.findByCustomerIdAndPizzaId(customerId, pizzaId).orElse(null);
            if (basketItem == null) {
                return VarList.RSP_NO_DATA_FOUND;
            }
            basketItemRepo.delete(basketItem);
            return VarList.RSP_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return VarList.RSP_ERROR;
        }
    }
}
