package com.example.myapigateway.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *  从 identityservice Copy
 *
 *  使用JWT 根据userName 生成 Token
 *
 * JWT是JSON WEB TOKEN
 * 它是基于 RFC 7519 标准定义的一种可以安全传输的的JSON对象，由于使用了数字签名，
 * 所以是可信任和安全的。总结来说，JWT只是一个生成token的机制。
 *
 * JWT token的格式：header.payload.signature
 * header中用于存放签名的生成算法: HS256
 * payload中用于存放用户名、token的生成时间和过期时间: name,exp
 * signature为以header和payload生成的签名，一旦header和payload被篡改，验证将失败
 *
 */
@Component
public class JwtTokenService {

    //加密使用
    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    /**
     * 验证 token
     */
    public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }

    /**
     * 根据 SECRET 字符串，生成 KEY
     */

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
