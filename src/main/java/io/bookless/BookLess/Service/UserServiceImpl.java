package io.bookless.BookLess.Service;

import io.bookless.BookLess.DTO.UserRegistrationDTO;
import io.bookless.BookLess.Entity.Role;
import io.bookless.BookLess.Entity.User;
import io.bookless.BookLess.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDTO registrationDTO) {

        User user = new User(registrationDTO.getFirstname(), registrationDTO.getLastname(), registrationDTO.getEmail(), registrationDTO.getPassword(), Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);
    }
}
