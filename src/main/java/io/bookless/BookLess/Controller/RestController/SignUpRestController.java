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

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/register")
    public ResponseEntity<Status> registerUser(@RequestBody UserModel userModel, final HttpServletRequest request) {

        User user = userService.registerUser(userModel);

        // List of already stored users
        List<User> users = userRepository.findAll();


        for (User useer : users) {

            // Checks if user already exists through email and if true returns user already exists
            if (useer.getEmail().equals(userModel.getEmail()) && useer.isEnabled()) {
                log.info("User Already exists!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Status.USER_ALREADY_EXISTS);
            }
            else if(useer.getEmail().equals(userModel.getEmail()) && !useer.isEnabled()) {
                log.info("Not Verified");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(Status.NOT_VERIFIED);
            }
        }
            userRepository.save(user);

        publisher.publishEvent(new SignupCompleteEvent(
                user, applicationUrl(request)
        ));
        return ResponseEntity.status(HttpStatus.OK).body(Status.SUCCESS);
    }

//    @GetMapping("/verifySignup")
//    @ResponseBody
//    public String verifyRegistration(@RequestParam("token") String token){
//        String result = userService.validateVerificationToken(token);
//        if(result.equalsIgnoreCase("valid"))
//            return "verified";
////            return ResponseEntity.status(HttpStatus.OK).body(Status.USER_VERIFIED_SUCCESSFULLY);
////        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(Status.BAD_USER);
//        return "bad";
//    }
//
//    @GetMapping("/resendVerifyToken")
//    public ResponseEntity<Status> reSendVerificationToken(@RequestParam("token") String oldToken, HttpServletRequest request){
//
//        VerificationToken verificationToken = userService.generateNewVerificationToken(oldToken);
//        User user = verificationToken.getUser();
//        reSendVerificationTokenMail(user, applicationUrl(request), verificationToken);
//        return ResponseEntity.status(HttpStatus.OK).body(Status.VERIFICATION_LINK_SENT);
//    }
//
//    private void reSendVerificationTokenMail(User user, String applicationUrl, VerificationToken verificationToken) {
//
//        String url = applicationUrl + "/verifySignup?token=" + verificationToken.getToken();
//
//        //Send Verification Email()
//        String content="<a href='"+url+"'>"+url+"</a>";
//        emailSenderService.sendSimpleEmail(user.getEmail(),"Thank you for registering on BookLess. Click on the link to verify yourself \n" + url, "User Verification");
//
//        log.info("CLick the link to verify:{}",url);
//    }
//
    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }


}
