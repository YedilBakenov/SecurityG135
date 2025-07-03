package kz.project.securityg135.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    @PreAuthorize("isAnonymous()")
    @GetMapping(value = "/sign-in")
    public String getLoginPage(){
        return "sign-in";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/")
    public String getMainPage(){
        return "index";
    }
}
