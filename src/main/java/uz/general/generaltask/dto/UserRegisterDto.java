package uz.general.generaltask.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.general.generaltask.entity.Attachment;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegisterDto {
    private String name;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String status;
}
