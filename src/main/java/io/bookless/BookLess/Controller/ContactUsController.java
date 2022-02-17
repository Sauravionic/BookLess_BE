package io.bookless.BookLess.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactUsController {

    @GetMapping("/contactus")
    public String contactUs() {
        return "contactus";
    }
}
