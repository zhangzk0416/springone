package springone.com.demo.demos.web.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @NonNull
    private String username;
    @NotBlank(message = "password不能为空")
    private String password;

}
