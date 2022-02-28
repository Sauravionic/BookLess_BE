package io.bookless.BookLess.Controller;

import io.bookless.BookLess.DTO.UserRegistrationDTO;
import io.bookless.BookLess.Service.UserService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLIntegrityConstraintViolationException;

@Controller
@RequestMapping("/signup")
public class SignUpController {



    private UserService userService;
    private boolean emailError = false;
    public SignUpController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDTO userRegistrationDTO() {
        return new UserRegistrationDTO();
    }

    @GetMapping
    public String signUp(Model model){
        model.addAttribute("emailExists", " ");
        return "signup";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDTO registrationDto, Model model) {
        try {
            userService.save(registrationDto);
        }
        catch(DataIntegrityViolationException e) {
            model.addAttribute("emailExists", "User already Exists !!!");
            emailError = true;
            return "signup";
        }
        return "redirect:/regsucess";
    }

}
