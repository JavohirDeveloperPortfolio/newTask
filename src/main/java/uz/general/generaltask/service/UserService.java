package uz.general.generaltask.service;

import org.springframework.stereotype.Service;
import uz.general.generaltask.dto.UserRegisterDto;
import uz.general.generaltask.entity.Role;
import uz.general.generaltask.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
//    User saveUser(User user);
    Long saveUser(UserRegisterDto user);
    User getUser(String username);
    User getUser(Long id);
    Role saveRole(Role role);
    User findByUsername(String username);
    Role findByRoleName(String roleName);
    void addRoleToUser(String username, String roleName);
    void refreshToken(HttpServletRequest request, HttpServletResponse response);
}