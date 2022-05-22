package uz.general.generaltask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.general.generaltask.entity.User;

import java.util.Map;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
    Optional<User> findByPhoneNumber(String phoneNumber);
}
