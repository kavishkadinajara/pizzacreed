package lk.kavi.nibm.pizzacreed.service;

import java.util.ArrayList;
import java.util.List;


import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.TransactionScoped;
import lk.kavi.nibm.pizzacreed.dto.AuthDTO;
import lk.kavi.nibm.pizzacreed.entity.Auth;
import lk.kavi.nibm.pizzacreed.repo.AuthRepo;
import lk.kavi.nibm.pizzacreed.util.VarList;

@Service
@TransactionScoped
public class AuthService {

    @Autowired
    private AuthRepo authRepo;

    @Autowired
    private ModelMapper modelMapper;

    // public String login(AuthDTO authDTO){
    //      try {
            
    //         if(authRepo.existsById(authDTO.getUserId())) {

    //             authRepo.save(modelMapper.map(authDTO, Auth.class));
    //             return VarList.RSP_SUCCESS;

    //         } else {
    //             return VarList.RSP_NO_DATA_FOUND;

    //         }
    //     } catch (Exception e) {
    //          // Log the exception for debugging purposes
    //          e.printStackTrace();
    //          // Return a generic error response
    //          return VarList.RSP_ERROR;
    //     }
    // }

     // GET ALL EMPLOYEES FROM TABLE
     public List<AuthDTO> getAll() {

        List<Auth> employeeList = authRepo.findAll();
        return modelMapper.map(employeeList, new TypeToken<ArrayList<AuthDTO>>(){}.getType());
    }

    

}
