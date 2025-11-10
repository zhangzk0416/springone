package springone.com.demo.demos.web.POJO.VO;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
public class LoginVO {

    private String username;
    private String name;
    private String phone;
    private String idcard;
    private String token;
    private int sex;
    private int age;

}
