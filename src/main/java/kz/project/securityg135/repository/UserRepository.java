package kz.project.securityg135.repository;

import jakarta.transaction.Transactional;
import kz.project.securityg135.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByEmail(String email);
}

