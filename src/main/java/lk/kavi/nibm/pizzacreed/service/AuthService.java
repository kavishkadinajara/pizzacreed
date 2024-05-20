package lk.kavi.nibm.pizzacreed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lk.kavi.nibm.pizzacreed.dto.AuthDTO;
import lk.kavi.nibm.pizzacreed.entity.Auth;
import lk.kavi.nibm.pizzacreed.repo.AuthRepo;
import lk.kavi.nibm.pizzacreed.util.VarList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
@Transactional
public class AuthService {

    @Autowired
    private AuthRepo authRepo;

    public String login(AuthDTO authDTO) {
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
}
