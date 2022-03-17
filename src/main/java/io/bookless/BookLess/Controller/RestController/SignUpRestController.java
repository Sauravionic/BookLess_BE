package io.bookless.BookLess.Controller.RestController;


import io.bookless.BookLess.Entity.User;
import io.bookless.BookLess.Entity.VerificationToken;
import io.bookless.BookLess.Enum.Status;
import io.bookless.BookLess.Event.SignupCompleteEvent;
import io.bookless.BookLess.Model.UserModel;
import io.bookless.BookLess.Repository.UserRepository;
import io.bookless.BookLess.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class SignUpRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public ResponseEntity<Status> registerUser(@RequestBody UserModel userModel, final HttpServletRequest request) {

        User user = userService.registerUser(userModel);

        // List of already stored users
        List<User> users = userRepository.findAll();


        for (User useer : users) {

            // Checks if user already exists through email and if true returns user already exists
            if (useer.getEmail().equals(user.getEmail())) {
                System.out.println("User Already exists!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Status.USER_ALREADY_EXISTS);
            }
        }
            userRepository.save(user);

        publisher.publishEvent(new SignupCompleteEvent(
                user, applicationUrl(request)
        ));
        return ResponseEntity.status(HttpStatus.OK).body(Status.SUCCESS);
    }

    @GetMapping("/verifySignup")
    public ResponseEntity<Status> verifyRegistration(@RequestParam("token") String token){
        String result = userService.validateVerificationToken(token);
        if(result.equalsIgnoreCase("valid"))
            return ResponseEntity.status(HttpStatus.OK).body(Status.USER_VERIFIED_SUCCESSFULLY);
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(Status.BAD_USER);
    }

    @GetMapping("/resendVerifyToken")
    public ResponseEntity<Status> reSendVerificationToken(@RequestParam("token") String oldToken, HttpServletRequest request){

        VerificationToken verificationToken = userService.generateNewVerificationToken(oldToken);
        User user = verificationToken.getUser();
        reSendVerificationTokenMail(user, applicationUrl(request), verificationToken);
        return ResponseEntity.status(HttpStatus.OK).body(Status.VERIFICATION_LINK_SENT);
    }

    private void reSendVerificationTokenMail(User user, String applicationUrl, VerificationToken verificationToken) {

        String url = applicationUrl + "/verifySignup?token=" + verificationToken.getToken();

        //Send Verification Email()
        log.info("CLick the link to verify:{}",url);
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }


}
