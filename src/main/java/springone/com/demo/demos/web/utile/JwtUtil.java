package springone.com.demo.demos.web.utile;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import springone.com.demo.demos.web.common.JwtProperties;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtil {


    public static String createJWT(String secretKey, long ttlMillis, Map<String, Object> claims) {
        // 指定签名使用的算法，HS256 是 HMAC SHA256 算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 计算过期时间
        long expMillis = System.currentTimeMillis() + ttlMillis;
        Date exp = new Date(expMillis);

        // 创建 JWT 的构建器
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)  // 设置 payload 中的 claim
                .signWith(signatureAlgorithm, secretKey.getBytes(StandardCharsets.UTF_8))  // 设置签名算法和密钥
                .setExpiration(exp);  // 设置过期时间

        // 返回生成的 JWT 字符串
        return builder.compact();
    }

    public static Claims parseJWT(String secretKey, String token) {
        // 解析 JWT，验证签名和获取载荷
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }


}
