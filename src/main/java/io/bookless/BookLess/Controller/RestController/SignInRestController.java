package io.bookless.BookLess.Controller.RestController;

import io.bookless.BookLess.Entity.User;
import io.bookless.BookLess.Entity.VerificationToken;
import io.bookless.BookLess.Enum.Status;
import io.bookless.BookLess.Event.SignupCompleteEvent;
import io.bookless.BookLess.Model.UserModel;
import io.bookless.BookLess.Repository.UserRepository;
import io.bookless.BookLess.Service.EmailSenderService;
import io.bookless.BookLess.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
public class SignInRestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/login")
    public ResponseEntity<Status> loginUser(@RequestBody UserModel userModel) {

        List<User> users = userRepository.findAll();


        for (User other : users) {

            if ((other.getEmail().equals(userModel.getEmail()) && bCryptPasswordEncoder.matches(userModel.getPassword(),other.getPassword())) && other.isEnabled()) {
                log.info("logged in");
                return ResponseEntity.status(HttpStatus.OK).body(Status.SUCCESS);
            }
            else if((other.getEmail().equals(userModel.getEmail()) && !bCryptPasswordEncoder.matches(userModel.getPassword(),other.getPassword()))){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Status.NOT_FOUND);
            }
            else if((other.getEmail().equals(userModel.getEmail()) && bCryptPasswordEncoder.matches(userModel.getPassword(),other.getPassword())) && !other.isEnabled()) {

                log.info("Not Verified");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(Status.NOT_VERIFIED);
        }

    }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Status.FAILURE);
    }


}
