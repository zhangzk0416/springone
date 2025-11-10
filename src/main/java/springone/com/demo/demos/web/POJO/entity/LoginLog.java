package springone.com.demo.demos.web.POJO.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginLog {
    private int id;
    private String username;
    private String name;
    private String phone;
    private String ip;
    private LocalDateTime loginTime;
    private int status;
}
