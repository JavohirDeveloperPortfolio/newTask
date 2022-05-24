package uz.general.generaltask.dto.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStatusStatisticsDao {
    private Long countOnline;
    private Long countOffline;
    private Long countUndefined;
}
