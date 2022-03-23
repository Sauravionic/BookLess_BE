package io.bookless.BookLess.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostLoginHomeController {

    @GetMapping("/home")
    public String postLoginHome() {
        return "PostLogin/index";
    }
}
