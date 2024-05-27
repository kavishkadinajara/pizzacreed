package lk.kavi.nibm.pizzacreed.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lk.kavi.nibm.pizzacreed.dto.AuthDTO;
import lk.kavi.nibm.pizzacreed.dto.CustomerDTO;
import lk.kavi.nibm.pizzacreed.dto.ResponseDTO;
import lk.kavi.nibm.pizzacreed.entity.Auth;
import lk.kavi.nibm.pizzacreed.entity.Customer;
import lk.kavi.nibm.pizzacreed.entity.ShoppingBasket;
import lk.kavi.nibm.pizzacreed.repo.AuthRepo;
import lk.kavi.nibm.pizzacreed.repo.CustomerRepo;
import lk.kavi.nibm.pizzacreed.repo.ShoppingBasketRepo;
import lk.kavi.nibm.pizzacreed.util.VarList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
@Transactional
public class AuthService {

    @Autowired
    private AuthRepo authRepo;
    @Autowired
    private ShoppingBasketRepo shoppingBasketRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private ModelMapper modelMapper;

    // ADMIN LOGIN
    public String adminLogin(AuthDTO authDTO) {
        try {
            if (authDTO.getUsername() == null || authDTO.getPassword() == null) {
                return VarList.RSP_FAIL;
            }

            Auth auth = authRepo.findByUsername(authDTO.getUsername());
            if (auth != null && auth.getPassword().equals(authDTO.getPassword())) {
                ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
                HttpServletRequest request = attr.getRequest();
                HttpSession session = request.getSession(true);

                session.setAttribute("username", auth.getUsername());
                session.setAttribute("userId", auth.getAuthId());

                authDTO.setAuthId(auth.getAuthId());

                return VarList.RSP_SUCCESS;
            } else {
                return VarList.RSP_FAIL;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error during login", e);
        }
    }

    // CUSTOMER LOGIN
    public String customerLogin(CustomerDTO customerDTO) {
        try {
            if (customerDTO.getCustomerEmail() == null || customerDTO.getPassword() == null) {
                return VarList.RSP_FAIL;
            }

            Customer customer = customerRepo.findByCustomerEmail(customerDTO.getCustomerEmail());
            if (customer != null && customer.getPassword().equals(customerDTO.getPassword())) {
                ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
                HttpServletRequest request = attr.getRequest();
                HttpSession session = request.getSession(true);

                session.setAttribute("username", customer.getCustomerEmail());
                session.setAttribute("userId", customer.getCustomerId());

                customerDTO.setCustomerId(customer.getCustomerId());

                return VarList.RSP_SUCCESS;
            } else {
                return VarList.RSP_FAIL;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error during login", e);
        }
    }


    // REGISTER CUSTOMER
    public String registerCustomer(CustomerDTO customerDTO) {
        try {
            // Check if any required fields are null
            if (customerDTO.getCustomerName() == null ||
                customerDTO.getCustomerEmail() == null ||
                customerDTO.getCustomerTele() == null ||
                customerDTO.getPassword() == null ||
                customerDTO.getCustomerAddress() == null) {
                return VarList.RSP_FAIL; // Return error response if any required field is null
            }

            // Check if customer with the same ID already exists
            if (customerRepo.existsById(customerDTO.getCustomerId())) {
                return VarList.RSP_DUPLICATED;
            } else {
                // Save the new customer and get the saved entity
                Customer savedCustomer = customerRepo.save(modelMapper.map(customerDTO, Customer.class));

                // Create a new shopping basket for the new customer
                ShoppingBasket shoppingBasket = new ShoppingBasket();
                shoppingBasket.setCustomerId(savedCustomer.getCustomerId());
                shoppingBasket.setTotalAmount(0.0); // Initialize total amount or set as needed
                shoppingBasketRepo.save(shoppingBasket);

                return VarList.RSP_SUCCESS;
            }
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            // Return a generic error response
            return VarList.RSP_ERROR;
        }
    }

}
