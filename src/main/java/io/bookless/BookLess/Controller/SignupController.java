package io.bookless.BookLess.Controller;

import io.bookless.BookLess.Entity.User;
import io.bookless.BookLess.Entity.VerificationToken;
import io.bookless.BookLess.Enum.Status;
import io.bookless.BookLess.Service.EmailSenderService;
import io.bookless.BookLess.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class SignupController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailSenderService emailSenderService;

    @GetMapping("/signup")
    public String signUp(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "signup";
        }

        return "redirect:/home";
    }

    @GetMapping("/verifySignup")
    public String verifyRegistration(@RequestParam("token") String token, Model model){
        String result = userService.validateVerificationToken(token);
        if(result.equalsIgnoreCase("valid"))
            return "verified";
        model.addAttribute("token",token);
        return "bad";
    }

    @GetMapping("/resendVerifyToken")
    public String reSendVerificationToken(@RequestParam("token") String oldToken, HttpServletRequest request){

        VerificationToken verificationToken = userService.generateNewVerificationToken(oldToken);
        User user = verificationToken.getUser();
        reSendVerificationTokenMail(user, applicationUrl(request), verificationToken);
        return "verification_link";
    }

    private void reSendVerificationTokenMail(User user, String applicationUrl, VerificationToken verificationToken) {

        String url = applicationUrl + "/verifySignup?token=" + verificationToken.getToken();

        //Send Verification Email()
        String content="<a href='"+url+"'>"+url+"</a>";
        emailSenderService.sendSimpleEmail(user.getEmail(),"Thank you for registering on BookLess. Click on the link to verify yourself \n" + url, "User Verification");

        log.info("CLick the link to verify:{}",url);
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
