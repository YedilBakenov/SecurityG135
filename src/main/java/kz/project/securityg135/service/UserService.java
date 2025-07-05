package kz.project.securityg135.service;

import kz.project.securityg135.model.Permission;
import kz.project.securityg135.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User findUserByLogin(String login);
    Permission getBasePermission();
    void addNewUser(User user, String rePassword);
    void changePassword(String oldPassword, String newPassword, String reNewPassword);

}
