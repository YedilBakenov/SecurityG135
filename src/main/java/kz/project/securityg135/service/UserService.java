package kz.project.securityg135.service;

import kz.project.securityg135.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User findUserByLogin(String login);
}
