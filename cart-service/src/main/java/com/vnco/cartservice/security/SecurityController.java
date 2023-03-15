package com.vnco.cartservice.security;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("auth")
@Slf4j
public class SecurityController {
    
    @RequestMapping ("/login/form")
    public String loginForm() {
        return "security/login";
    }
    
    @RequestMapping ("/login/fail")
    public String loginFail(Model model) {
        model.addAttribute("failMessage", "Vui lòng kiểm tra lại tên đăng nhập hoặc mật khẩu");
        return "forward:/auth/login/form";
    }
    
    @RequestMapping ("/login/success")
    public String loginSuccess(HttpSession session) {
        String previousUri = (String) session.getAttribute("previousUri");
        log.info(">> PreviousUri: {}", previousUri);
        return previousUri != null ? "redirect:" + previousUri : "redirect:/";
    }
    
    @RequestMapping ("/logout/success")
    public String logoutSuccess(HttpSession session) {
        String previousUri = (String) session.getAttribute("previousUri");
        return previousUri != null ? "redirect:" + previousUri : "redirect:/";
    }
}
