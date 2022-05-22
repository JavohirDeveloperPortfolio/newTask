package uz.general.generaltask.controller;

import org.springframework.web.multipart.MultipartFile;
import uz.general.generaltask.dto.UserRegisterDto;
import uz.general.generaltask.entity.Attachment;
import uz.general.generaltask.service.AttachmentService;
import uz.general.generaltask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.general.generaltask.dto.AddRoleDto;
import uz.general.generaltask.entity.Role;
import uz.general.generaltask.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AttachmentService attachmentService;

   @PostMapping("/register")
    public ResponseEntity<Long> signUpUser(
            @RequestParam("image") MultipartFile file,
            @RequestBody UserRegisterDto user
    ){
        Attachment attachment = attachmentService.uploadAttachment(file);
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody UserRegisterDto user){
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

    @PostMapping("/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
        userService.refreshToken(request, response);
    }
}

