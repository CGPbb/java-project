package com.changgou.system.util;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

public class JwtUtil {
    //有效期为
    public static final Long JWT_TTL = 3600000L;
    //设置秘钥明文
    public static final String JWT_KEY="it";

    public static String createJWT(String id,String subject,Long ttlMillis){
        long l = System.currentTimeMillis();
        if (ttlMillis==null){
            ttlMillis=JwtUtil.JWT_TTL;
        }
        long expMillis = l + ttlMillis;

        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setSubject(subject)
                .setIssuer("admin")
                .setIssuedAt(new Date(l))
                .signWith(SignatureAlgorithm.HS256,generalKey())
                .setExpiration(new Date(expMillis))
                .claim("role","admin");
        return builder.compact();
    }

    public static SecretKey generalKey(){
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
}
