package io.bookless.BookLess.Service;

import io.bookless.BookLess.DTO.UserRegistrationDTO;
import io.bookless.BookLess.Entity.User;

public interface UserService {

    User save(UserRegistrationDTO registrationDTO);
}
