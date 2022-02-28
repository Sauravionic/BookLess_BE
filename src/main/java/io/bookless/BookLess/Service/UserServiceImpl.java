package io.bookless.BookLess.Service;

import io.bookless.BookLess.DTO.UserRegistrationDTO;
import io.bookless.BookLess.Entity.Role;
import io.bookless.BookLess.Entity.User;
import io.bookless.BookLess.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDTO registrationDTO) {

        String encodedPassword = bCryptPasswordEncoder.encode(registrationDTO.getPassword());
        User user = new User(registrationDTO.getFirstname(), registrationDTO.getLastname(), registrationDTO.getEmail(), encodedPassword, Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);
    }

}
