package uz.general.generaltask.service.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import uz.general.generaltask.dto.UserRegisterDto;
import uz.general.generaltask.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.general.generaltask.entity.Role;
import uz.general.generaltask.entity.User;
import uz.general.generaltask.repository.RoleRepository;
import uz.general.generaltask.repository.UserRepository;
import uz.general.generaltask.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

//    @Override
//    public User saveUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return userRepository.save(user);
//    }

    @Override
    public Long saveUser(UserRegisterDto userRegisterDto) {

//        if (checkUsername(userRegisterDto.getUsername()) != null){
//            new RuntimeException(String.format("%s Username already exist",userRegisterDto.getUsername()));
//        }
//
//        if (checkEmail(userRegisterDto.getEmail()) != null){
//            new RuntimeException(String.format("%s Email already exist",userRegisterDto.getEmail()));
//        }
//
//        if (checkPhoneNumber(userRegisterDto.getPhoneNumber()) != null){
//            new RuntimeException(String.format("%s Phone number already exist",userRegisterDto.getPhoneNumber()));
//        }

        return userRepository.save(
                    new User(
                    userRegisterDto.getName(),
                    userRegisterDto.getUsername(),
                    passwordEncoder.encode(userRegisterDto.getPassword()),
                    userRegisterDto.getEmail(),
                    userRegisterDto.getPhoneNumber()
            )
        ).getId();
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException(String.format("User: %s is not found",username)));
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("User: %s is not found",id)));
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException(String.format("User: %s is not found",username)));
    }

    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException(String.format("Role: %s is not found",roleName)));
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = findByUsername(username);
        Role role = findByRoleName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
        try {
            String token = JwtUtil.getTokenFromRequestHeader(request, response);

            //checking validation
            DecodedJWT decodedJWT = JwtUtil.validateToken(token, response);
            if (decodedJWT == null){
                return;
            }

            String username = decodedJWT.getSubject();
            User user = getUser(username);
            String accessToken = JwtUtil.generateAccessToken(user);

            HashMap<String, String> tokens = new HashMap<>();
            tokens.put("accessToken", accessToken);
            tokens.put("refreshToken", token);

            new ObjectMapper().writeValue(response.getOutputStream(), tokens);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public User checkUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    public User checkEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    public User checkPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber).get();
    }
}

