package springone.com.demo.demos.web.POJO.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {

    private Integer id;
    private String username;
    private String name;
    private String phone;
    private String idCard;
    private int sex;
    private int age;
}
