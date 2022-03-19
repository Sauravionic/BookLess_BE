package io.bookless.BookLess.Event.Listener;


import io.bookless.BookLess.Entity.User;
import io.bookless.BookLess.Event.SignupCompleteEvent;
import io.bookless.BookLess.Service.EmailSenderService;
import io.bookless.BookLess.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class SignupCompleteEventListener implements ApplicationListener<SignupCompleteEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Override
    public void onApplicationEvent(SignupCompleteEvent event) {

        //Create the verification token for the user with link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token,user);

        //Send mail to user
        String url = event.getApplicationUrl() + "/verifySignup?token=" + token;
        emailSenderService.sendSimpleEmail(user.getEmail(),"Thank you for registering on BookLess. Click on the link to verify yourself \n" + url, "User Verification");
        log.info("CLick the link to verify:{}",url);
    }
}
