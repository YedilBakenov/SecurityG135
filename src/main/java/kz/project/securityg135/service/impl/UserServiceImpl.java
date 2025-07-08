package kz.project.securityg135.service.impl;

import kz.project.securityg135.model.Permission;
import kz.project.securityg135.model.User;
import kz.project.securityg135.repository.PermissionRepository;
import kz.project.securityg135.repository.UserRepository;
import kz.project.securityg135.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findUserByLogin(String login) {
        return userRepository.findUserByEmail(login);
    }

    @Override
    public Permission getBasePermission() {
        return permissionRepository.getBaseRole();
    }

    @Override
    public String addNewUser(User user, String rePassword) {
        User userFromBase = userRepository.findUserByEmail(user.getEmail());

        if(userFromBase!=null) return "email_exist";

        if(!user.getPassword().equals(rePassword)) return "password_not_equals";

        user.setPermissions(List.of(getBasePermission()));
        user.setPassword(passwordEncoder.encode(rePassword));

        userRepository.save(user);

        return "user_created";
    }

    public User getCurrentUserInSession(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    @Override
    public void changePassword(String oldPassword, String newPassword, String reNewPassword) {

            if(!passwordEncoder.matches(oldPassword, getCurrentUserInSession().getPassword())) return;

            if(!newPassword.equals(reNewPassword)) return;

            getCurrentUserInSession().setPassword(passwordEncoder.encode(newPassword));

            userRepository.save(getCurrentUserInSession());

    }
}
