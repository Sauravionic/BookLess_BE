package io.bookless.BookLess.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SigninController {

    @GetMapping("/signin")
    public String signin(){
        return "signin";
    }

}
