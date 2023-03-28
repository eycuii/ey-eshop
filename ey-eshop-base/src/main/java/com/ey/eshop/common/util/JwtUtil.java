package com.ey.eshop.common.util;

import com.ey.eshop.common.exception.SessionException;
import io.jsonwebtoken.*;

import java.util.Date;

public class JwtUtil {

    public static final String JWT_HEADER_KEY = "Authorization";
    private static final String JWT_HEADER_PREFIX = "Bearer ";
    private static final String JWT_SECRET = "Dh37b4yt55Xex2rc";
    private static final int JWT_EXPIRATION = 60 * 60 * 24 * 1000;

    public static String generateToken(Long userId) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(SecurityUtil.encrypt(String.valueOf(userId), JWT_SECRET))
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + JWT_EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public static Long validateToken(String header) {
        if (StringUtil.isEmpty(header)) {
            throw new SessionException("请先登录");
        }
        try {
            String jwt = header.substring(JWT_HEADER_PREFIX.length());
            Jws<Claims> claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(jwt);
            return Long.valueOf(SecurityUtil.decrypt(claims.getBody().getSubject(), JWT_SECRET));
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            throw new SessionException("无效的凭证");
        } catch (ExpiredJwtException ex) {
            throw new SessionException("登录凭证已过期");
        }
    }
}
