package lk.kavi.nibm.pizzacreed.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lk.kavi.nibm.pizzacreed.repo.AuthRepo;
import lk.kavi.nibm.pizzacreed.dto.AuthDTO;
import lk.kavi.nibm.pizzacreed.entity.Auth;
import lk.kavi.nibm.pizzacreed.util.VarList;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.UUID;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
@Transactional
public class AuthService {

    private static final Logger logger = Logger.getLogger(AuthService.class.getName());

    @Autowired
    private AuthRepo authRepo;

    @Autowired
    private ModelMapper modelMapper;

    public String login(AuthDTO authDTO) {
        try {
            if (authDTO.getUsername() == null || authDTO.getPassword() == null) {
                logger.log(Level.WARNING, "Username or password is null");
                return VarList.RSP_FAIL;
            }

            Auth auth = authRepo.findByUsername(authDTO.getUsername());
            if (auth != null && auth.getPassword().equals(authDTO.getPassword())) {
                ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
                HttpServletRequest request = attr.getRequest();
                HttpSession session = request.getSession(true);

                session.setAttribute("username", auth.getUsername());
                session.setAttribute("userId", auth.getAuthId());

                // Set role and generate API key for admin users
                if ("admin".equals(auth.getUsername())) {
                    authDTO.setRole("admin");
                    String apiKey = generateAPIKey();
                    session.setAttribute("apiKey", apiKey);
                } else {
                    authDTO.setRole("user");
                }

                authDTO.setAuthId(auth.getAuthId());

                // Log session attributes
                logger.log(Level.INFO, "Session started for user: {0}", auth.getUsername());
                logger.log(Level.INFO, "Session ID: {0}", session.getId());
                logger.log(Level.INFO, "Session Attribute 'username': {0}", session.getAttribute("username"));
                logger.log(Level.INFO, "Session Attribute 'userId': {0}", session.getAttribute("userId"));

                return VarList.RSP_SUCCESS;
            } else {
                logger.log(Level.WARNING, "Invalid credentials for user {0}", authDTO.getUsername());
                return VarList.RSP_FAIL;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error during login", e);
            throw e; // Re-throw the exception to ensure transaction rollback
        }
    }

    private String generateAPIKey() {
        return UUID.randomUUID().toString();
    }

    // GET ALL USERS FROM TABLE
    public List<AuthDTO> getAll() {
        List<Auth> employeeList = authRepo.findAll();
        return modelMapper.map(employeeList, new TypeToken<ArrayList<AuthDTO>>(){}.getType());
    }
}
