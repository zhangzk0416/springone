package springone.com.demo.demos.web.POJO.DTO;

import lombok.Data;

@Data
public class CreateUserDto {
    private Integer id;
    private String username;
    private String name;
    private String password;
    private String phone;
    private String idCard;
    private int sex;
    private int age;
}
