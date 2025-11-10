package springone.com.demo.demos.web.POJO.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserListVo {
    private String username;
    private String name;
    private String phone;
    private LocalDateTime lastLoginDate;
}
