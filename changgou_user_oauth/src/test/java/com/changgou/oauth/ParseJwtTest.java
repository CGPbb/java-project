package com.changgou.oauth;

import org.junit.Test;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;

public class ParseJwtTest {
    /***
     * 校验令牌
     */
    @Test
    public void testParseToken() {
        //令牌
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlcyI6IlJPTEVfVklQLFJPTEVfVVNFUiIsIm5hbWUiOiJpdCIsImlkIjoiMSJ9.WmOxcEyt-mDXH88ZaNZ7oYc5E9yb3SV9LItkAPIu2cgyHr-nVysuibjszR9aHk6Pj568hvfaizNTWYTXc6yg7rmbm5m5Oi5ZcLMh6PN10i7GvSd-fXogUqU0ug9ReTuAoeiKcrAAuDx0u_bqcko5tbKbzK3P41506xFB-4FNeWmhJvHeBMYqV6GgWo31xl6WyYmTF20DCRtGZ0Eu9cDNfd6wDNQPfiDbTTJeqbvA8d4DCSSZCt_4jyl9v8z28Eri54BoGHAGUS0bmbOX9iiqFJSq63bUUhuvN2_brfZZ-8jQ66mvQ21iv26wVmrr7uRb9WpTqCtrgHKiy9CTA-cOog";
        String publickey = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApd6MpmmJGkF/EpMufZKazYsg7xi6kUtQ6AnCkOkpb0TDTMnMbdCpsWQMIvPs/ILV7LnlOO8SFjlMsrA2ZisRcsk3EXAkYW+6irFtUdPb3b/jRHEbyDYjOm0JvOZztGPSKStoF5Rx/DdT5Ff1D3kzEqv8HykWE5Dqlf7lzefhyY4RWiQB04gxlVEs1Dmb9nrFVodPcAePkEAs5/nUnBhgKnMp+ArVptlO4Mkh4y6p3rdSwH2xFdtDX1Eh3laG8BUxiRA3vZZd+LMtT0Wguhz+PhTanRPCW/3H2tVdDiw61LeSOfp1J2QSnVzobWJWZEazkT5ZxXp2q/EnMW2JUHV7+wIDAQAB-----END PUBLIC KEY-----";
        //校验Jwt
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(publickey));
        //获取Jwt原始内容
        String claims = jwt.getClaims();
        System.out.println(claims);
        //jwt令牌
        String encoded = jwt.getEncoded();
        System.out.println(encoded);
    }
}

