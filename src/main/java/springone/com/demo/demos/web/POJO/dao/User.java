package springone.com.demo.demos.web.POJO.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @NonNull
    private String username;

    private String password;

}
