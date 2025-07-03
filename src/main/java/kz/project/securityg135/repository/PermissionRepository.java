package kz.project.securityg135.repository;

import jakarta.transaction.Transactional;
import kz.project.securityg135.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
}
