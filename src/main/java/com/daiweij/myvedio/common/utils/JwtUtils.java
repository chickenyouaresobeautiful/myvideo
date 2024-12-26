package com.daiweij.myvedio.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

public class JwtUtils {
    private static final String SECRET_KEY = "uT56d/djV0hvEk0HylBv2lT5bEX5JrzjFZSkXk2kAd8=";

    public static String generateToken(Long userId, String username) {
        return Jwts.builder()
                .subject(username)
                .claim("userId", userId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 设置过期时间
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY)))
                .compact();
    }

    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY)))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 获取用户名
    public static String getUsernameFromToken(String token) {
        return parseToken(token).getSubject();  // 获取 Subject，也就是用户名
    }

    // 获取用户 ID
    public static Long getUserIdFromToken(String token) {
        return (Long) parseToken(token).get("userId");  // 获取自定义的 userId claim
    }

    // 验证 Token 是否过期
    public static boolean isTokenExpired(String token) {
        return parseToken(token).getExpiration().before(new Date());  // 比较过期时间
    }

    // 验证 Token 是否有效
    public static boolean validateToken(String token) {
        return !isTokenExpired(token);  // 简单判断是否过期
    }
}
