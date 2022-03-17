package io.bookless.BookLess.Event.Listener;


import io.bookless.BookLess.Entity.User;
import io.bookless.BookLess.Event.SignupCompleteEvent;
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

    @Override
    public void onApplicationEvent(SignupCompleteEvent event) {

        //Create the verification token for the user with link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token,user);

        //Send mail to user
        String url = event.getApplicationUrl() + "/verifySignup?token=" + token;

        //Send Verification Email()
        log.info("CLick the link to verify:{}",url);
    }
}
