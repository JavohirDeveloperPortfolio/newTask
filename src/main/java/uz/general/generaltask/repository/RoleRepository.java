package uz.general.generaltask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.general.generaltask.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
}
