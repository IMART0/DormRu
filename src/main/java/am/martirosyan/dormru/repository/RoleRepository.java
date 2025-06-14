package am.martirosyan.dormru.repository;

import am.martirosyan.dormru.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r WHERE r.name = 'ROLE_USER'")
    Role findUserRole();

    Optional<Role> findRoleByName(Role.RoleName name);
}
