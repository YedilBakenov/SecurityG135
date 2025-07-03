package kz.project.securityg135.service.impl;

import kz.project.securityg135.model.User;
import kz.project.securityg135.repository.UserRepository;
import kz.project.securityg135.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User findUserByLogin(String login) {
        return userRepository.findUserByEmail(login);
    }
}
