package io.bookless.BookLess.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegSuccessController {

    @GetMapping("/regsucess")
    public String regSucess() {
        return "regsucess";
    }
}
