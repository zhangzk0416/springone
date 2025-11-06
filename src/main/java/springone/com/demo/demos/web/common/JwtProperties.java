package springone.com.demo.demos.web.common;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties("jwt")
@NoArgsConstructor
@AllArgsConstructor
public class JwtProperties {

    private String secret;
    private String expiration;
    private String tokenName;
}
