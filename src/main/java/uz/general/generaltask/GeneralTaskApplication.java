package uz.general.generaltask;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.general.generaltask.dto.UserRegisterDto;
import uz.general.generaltask.entity.Role;
import uz.general.generaltask.service.UserService;

@SpringBootApplication
public class GeneralTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeneralTaskApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner run(UserService userService){
        return args -> {
            userService.saveRole(new Role("ROLE_USER"));
            userService.saveRole(new Role("ROLE_MANAGER"));
            userService.saveRole(new Role( "ROLE_ADMIN"));

            userService.saveUser(new UserRegisterDto("Admin","admin","root","admin@admin.uz","+998933453368","online"));
            userService.saveUser(new UserRegisterDto("Manager","manager","root","manager@admin.uz","+998933453368","online"));

            userService.addRoleToUser("admin","ROLE_ADMIN");
            userService.addRoleToUser("manager","ROLE_MANAGER");
        };
    }

}
