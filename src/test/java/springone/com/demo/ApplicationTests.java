package springone.com.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springone.com.demo.demos.web.common.JwtProperties;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private JwtProperties jwtProperties;

    @Test
    void contextLoads() {
        System.out.println(jwtProperties.toString());

    }

}
