package kz.project.securityg135.controller;

import jakarta.websocket.server.ServerEndpoint;
import kz.project.securityg135.model.User;
import kz.project.securityg135.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

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

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/forbidden")
    public String forbiddenPage(){
        return "forbidden";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String adminPage(){
        return "admin-page";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping(value = "/registration")
    public String registerPage(){
        return "register-page";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping(value = "/registration")
    public String register(User user, @RequestParam String rePassword){

        String result =  userService.addNewUser(user, rePassword);

        return "redirect:/registration?" + result;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/change-pas")
    public String changePas(@RequestParam String oldPas,
                            @RequestParam String newPas,
                            @RequestParam String reNewPas){

        userService.changePassword(oldPas, newPas, reNewPas);

        return "sign-in";
    }
}
