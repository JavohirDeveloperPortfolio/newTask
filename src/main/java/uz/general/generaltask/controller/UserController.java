package uz.general.generaltask.controller;

import org.springframework.web.multipart.MultipartFile;
import uz.general.generaltask.dto.ChangeStatusDto;
import uz.general.generaltask.dto.UserRegisterDto;
import uz.general.generaltask.dto.dao.ChangeStatusDao;
import uz.general.generaltask.entity.Attachment;
import uz.general.generaltask.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.general.generaltask.dto.AddRoleDto;
import uz.general.generaltask.entity.Role;
import uz.general.generaltask.entity.User;
import uz.general.generaltask.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AttachmentService attachmentService;

    @PostMapping("/signUpWithImage")
    public ResponseEntity<Long> signUpUser(
            @RequestParam("image") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("email") String email,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("status") String status
    ){
//        if (userService.checkUsername(userRegisterDto.getUsername()) != null){
//            new RuntimeException(String.format("%s Username already exist",userRegisterDto.getUsername()));
//        }
//
//        if (userService.checkEmail(userRegisterDto.getEmail()) != null){
//            new RuntimeException(String.format("%s Email already exist",userRegisterDto.getEmail()));
//        }
//
//        if (userService.checkPhoneNumber(userRegisterDto.getPhoneNumber()) != null){
//            new RuntimeException(String.format("%s Phone number already exist",userRegisterDto.getPhoneNumber()));
//        }

        Attachment attachment = attachmentService.uploadAttachment(file);
        List<Role> roles = List.of(userService.findByRoleName("ROLE_USER"));
        User user = new User(
                name,
                username,
                password,
                email,
                phoneNumber,
                status,
                roles,
                attachment
        );
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PostMapping("/signUp")
    public ResponseEntity<Long> signUpUser(
            @RequestBody UserRegisterDto userRegisterDto
    ){
        List<Role> roles = List.of(userService.findByRoleName("ROLE_USER"));

        return ResponseEntity.ok(userService.saveUser(new User(
                userRegisterDto.getName(),
                userRegisterDto.getUsername(),
                userRegisterDto.getPassword(),
                userRegisterDto.getEmail(),
                userRegisterDto.getPhoneNumber(),
                userRegisterDto.getStatus(),
                roles,
                attachmentService.getAttachment(userRegisterDto.getAttachmentId())
        )));
    }

    @PostMapping("/editStatus")
    public ResponseEntity<ChangeStatusDao> editStatus(
            @RequestBody ChangeStatusDto statusDto
    ){
        return ResponseEntity.ok(userService.changeStatus(statusDto));
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/role/save")
    public ResponseEntity<?> saveRole(@RequestBody Role role){
        return ResponseEntity.ok(userService.saveRole(role));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    @PostMapping("/add/role")
    public ResponseEntity<?> addRoleToUser(@RequestBody AddRoleDto form){
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping("/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
        userService.refreshToken(request, response);
    }
}

