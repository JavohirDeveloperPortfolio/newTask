package uz.general.generaltask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.general.generaltask.entity.User;

import java.util.Map;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
    Optional<User> findByPhoneNumber(String phoneNumber);

    @Query(value = "SELECT count(*) FROM users u WHERE u.status = 'online'", nativeQuery = true)
    Long findAllActiveUsers();

    @Query(value = "SELECT count(*) FROM users u WHERE u.status = 'offline'", nativeQuery = true)
    Long findAllInactiveUsers();

    @Query(value = "SELECT count(*) FROM users u WHERE u.status <> 'offline' and u.status <> 'online'", nativeQuery = true)
    Long findAllUndefinedUsers();
}
