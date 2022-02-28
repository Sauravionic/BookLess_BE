package io.bookless.BookLess.Service;

import io.bookless.BookLess.DTO.UserRegistrationDTO;
import io.bookless.BookLess.Entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService{
    User save(UserRegistrationDTO registrationDTO);
}
