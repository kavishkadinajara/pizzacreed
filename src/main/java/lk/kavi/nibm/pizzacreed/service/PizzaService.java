package lk.kavi.nibm.pizzacreed.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import lk.kavi.nibm.pizzacreed.repo.PizzaRepo;
import lk.kavi.nibm.pizzacreed.repo.PizzaSizeRepo;
import lk.kavi.nibm.pizzacreed.util.VarList;
import lk.kavi.nibm.pizzacreed.dto.PizzaDTO;
import lk.kavi.nibm.pizzacreed.dto.PizzaDetailsDTO;
import lk.kavi.nibm.pizzacreed.dto.PizzaSizeDTO;
import lk.kavi.nibm.pizzacreed.entity.Pizza;
import lk.kavi.nibm.pizzacreed.entity.PizzaSize;

@Service
@Transactional
public class PizzaService {

    @Autowired
    private PizzaRepo pizzaRepo;
    
    @Autowired
    private PizzaSizeRepo pizzaSizeRepo;

    @Autowired
    private ModelMapper modelMapper;

    // GET ALL PIZZAS FROM TABLE
    public List<PizzaDetailsDTO> getAllPizzas() {
        List<Object[]> pizzaDetailsList = pizzaRepo.getAllPizzaDetails();
        Map<Integer, PizzaDetailsDTO> pizzaMap = new HashMap<>();

        pizzaDetailsList.forEach(record -> {
            try {
                int pizzaId = (Integer) record[0];
                PizzaDetailsDTO pizzaDetailsDTO = pizzaMap.getOrDefault(pizzaId, new PizzaDetailsDTO());

                pizzaDetailsDTO.setPizzaId(pizzaId);
                pizzaDetailsDTO.setPizzaName((String) record[1]);
                pizzaDetailsDTO.setPizzaDiscription((String) record[2]);
                pizzaDetailsDTO.setPizzaImg((String) record[3]);

                if (pizzaDetailsDTO.getSizes() == null) {
                    pizzaDetailsDTO.setSizes(new ArrayList<>());
                }

                if (record[4] != null && record[5] != null) {
                    PizzaSizeDTO sizeDTO = new PizzaSizeDTO();
                    sizeDTO.setSizeId((Integer) record[4]);
                    sizeDTO.setPrice((Double) record[5]);
                    pizzaDetailsDTO.getSizes().add(sizeDTO);
                }

                pizzaMap.put(pizzaId, pizzaDetailsDTO);
            } catch (IndexOutOfBoundsException e) {
                // Handle the exception and log it
                System.err.println("Index out of bounds for record: " + java.util.Arrays.toString(record));
            } catch (Exception e) {
                // Handle other possible exceptions
                System.err.println("Error processing record: " + java.util.Arrays.toString(record));
            }
        });

        return new ArrayList<>(pizzaMap.values());
    }

    // GET ALL PIZZAS FROM TABLE
    public List<PizzaDTO> getAllEmployees() {
        List<Pizza> employeeList = pizzaRepo.findAll();
        return modelMapper.map(employeeList, new TypeToken<ArrayList<PizzaDTO>>() {}.getType());
    }

    // ADD NEW PIZZA
    @Transactional
    public String addPizza(PizzaDTO pizzaDTO) {
        try {
            // Save pizza
            Pizza pizza = modelMapper.map(pizzaDTO, Pizza.class);
            Pizza savedPizza = pizzaRepo.save(pizza);

            // Get the ID of the last inserted pizza
            int lastInsertedPizzaId = savedPizza.getPizzaId();

            // Save pizza sizes
            for (PizzaSizeDTO sizePizzaDTO : pizzaDTO.getSizes()) {
                PizzaSize pizzaSize = new PizzaSize();
                pizzaSize.setPizzaId(lastInsertedPizzaId); // Use the ID of the last inserted pizza
                pizzaSize.setSizeId(sizePizzaDTO.getSizeId());
                pizzaSize.setPizzaPrice(sizePizzaDTO.getPrice());
                pizzaSizeRepo.save(pizzaSize);
            }

            return VarList.RSP_SUCCESS;
        } catch (DataIntegrityViolationException e) {
            // This exception indicates a duplicate entry or constraint violation
            return VarList.RSP_DUPLICATED;
        } catch (NullPointerException e) {
            // This exception indicates missing required fields
            return VarList.RSP_FAIL;
        } catch (Exception e) {
            e.printStackTrace();
            return VarList.RSP_ERROR;
        }
    }

    // UPDATE PIZZA
    public String updatePizza(PizzaDTO pizzaDTO) {
        try {
            if (pizzaDTO.getPizzaName() == null || pizzaDTO.getPizzaDiscription() == null || pizzaDTO.getPizzaImg() == null) {
                return VarList.RSP_FAIL;
            }
            Pizza pizza = pizzaRepo.findById(pizzaDTO.getPizzaId()).orElse(null);
            if (pizza == null) {
                return VarList.RSP_NO_DATA_FOUND;
            }

            pizza.setPizzaName(pizzaDTO.getPizzaName());
            pizza.setPizzaDiscription(pizzaDTO.getPizzaDiscription());
            pizza.setPizzaImg(pizzaDTO.getPizzaImg());
            pizza.setCategoryId(pizzaDTO.getCategoryId());

            // Get existing sizes for the pizza
            List<PizzaSize> existingSizes = pizzaSizeRepo.findByPizzaId(pizzaDTO.getPizzaId());

            // Map existing sizes by sizeId for easy lookup
            Map<Integer, PizzaSize> existingSizeMap = existingSizes.stream()
                    .collect(Collectors.toMap(PizzaSize::getSizeId, size -> size));

            // Update or add new sizes
            for (PizzaSizeDTO sizePizzaDTO : pizzaDTO.getSizes()) {
                PizzaSize pizzaSize = existingSizeMap.getOrDefault(sizePizzaDTO.getSizeId(), new PizzaSize());
                pizzaSize.setPizzaId(pizzaDTO.getPizzaId());
                pizzaSize.setSizeId(sizePizzaDTO.getSizeId());
                pizzaSize.setPizzaPrice(sizePizzaDTO.getPrice());
                pizzaSizeRepo.save(pizzaSize);
                existingSizeMap.remove(sizePizzaDTO.getSizeId()); // Remove updated/added size from the map
            }

            // Remove sizes that are no longer associated with the pizza
            for (PizzaSize sizeToRemove : existingSizeMap.values()) {
                pizzaSizeRepo.delete(sizeToRemove);
            }

            pizzaRepo.save(pizza);
            return VarList.RSP_SUCCESS;
        } catch (Exception e) {
            throw new RuntimeException("Error updating pizza", e);
        }
    }
}
