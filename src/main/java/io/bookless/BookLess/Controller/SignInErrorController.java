//package io.bookless.BookLess.Controller;
//
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.WebAttributes;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//@Controller
//public class SignInErrorController {
//
//    @GetMapping("/signin?error")
//    public String login(HttpServletRequest request, Model model) {
//        HttpSession session = request.getSession(false);
//        String errorMessage = null;
//        if (session != null) {
//            AuthenticationException ex = (AuthenticationException) session
//                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
//            if (ex != null) {
//                errorMessage = ex.getMessage();
//            }
//        }
//        model.addAttribute("errorMessage", errorMessage);
//        return "signin";
//    }
//}
