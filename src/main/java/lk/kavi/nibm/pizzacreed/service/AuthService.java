package lk.kavi.nibm.pizzacreed.service;

// import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lk.kavi.nibm.pizzacreed.repo.AuthRepo;
import lk.kavi.nibm.pizzacreed.dto.AuthDTO;
import lk.kavi.nibm.pizzacreed.entity.Auth;
import lk.kavi.nibm.pizzacreed.util.VarList;

@Service
@Transactional
public class AuthService {

    @Autowired
    private AuthRepo authRepo;

    // @Autowired
    // private ModelMapper modelMapper;

    // LOGIN FUNCTION
    public String login(AuthDTO authDTO) {
        try {
            // Check if any required fields are null
            if (authDTO.getUsername() == null || authDTO.getPassword() == null) {
                return VarList.RSP_FAIL; // Return error response if any required field is null
            }

            // Find Auth entity by username
            Auth auth = authRepo.findByUsername(authDTO.getUsername());
            if (auth != null && auth.getPassword().equals(authDTO.getPassword())) {
                return VarList.RSP_SUCCESS;  // Login successful
            } else {
                return VarList.RSP_FAIL;  // Invalid credentials
            }
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            // Return a generic error response
            return VarList.RSP_ERROR;
        }
    }
}
