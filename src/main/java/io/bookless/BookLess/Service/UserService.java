package io.bookless.BookLess.Service;

import io.bookless.BookLess.Entity.User;
import io.bookless.BookLess.Entity.VerificationToken;
import io.bookless.BookLess.Model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, User user);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);
}
