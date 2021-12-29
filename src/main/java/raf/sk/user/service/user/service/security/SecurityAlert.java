package raf.sk.user.service.user.service.security;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import raf.sk.user.service.user.service.security.service.TokenService;

@Aspect
@Configuration
public class SecurityAlert {

    @Value("${oauth.jwt.secret}")
    private String jwtSecret;

    private TokenService tokenService;



}
