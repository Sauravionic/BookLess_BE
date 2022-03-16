package io.bookless.BookLess.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewArrivalsController {

    @GetMapping("/newarrivals")
    public String newArrivals(){
        return "newarrivals";
    }
}
