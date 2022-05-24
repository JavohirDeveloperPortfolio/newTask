package uz.general.generaltask.dto.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeStatusDao{
    private Long userId;
    private String newStatus;
    private String oldStatus;
}