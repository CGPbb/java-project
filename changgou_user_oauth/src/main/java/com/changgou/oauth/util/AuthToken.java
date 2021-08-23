package com.changgou.oauth.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthToken implements Serializable {

    //令牌信息 jwt
    String accessToken;

    //刷新 token
    String refreshToken;

    //jwt 短令牌
    String jti;

}
