package edu.nechaev.project.security;

import edu.nechaev.project.models.MemberRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {
    @Value("${jwt.token.secret}")
    private String secretString;
    @Value("${jwt.token.expired}")
    private long validityInMilliseconds;
    @Value("${jwt.refreshToken.expired}")
    private long refreshTokenValidityInMilliseconds;

    @Autowired
    private UserDetailsService userDetailsService;

    @SneakyThrows
    private Key getSignKey() {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        byte[] keyBytes = new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        String algorithm = "RawBytes";
        SecretKeySpec key = new SecretKeySpec(keyBytes, algorithm);

        sha256_HMAC.init(key);
        byte[] secretBytes = sha256_HMAC.doFinal(secretString.getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(secretBytes, "HmacSHA256");
    }


    public String createToken(String email, List<MemberRole> memberRoleList) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("roles", memberRoleList.stream().map(MemberRole::getName).collect(Collectors.toList()));

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(getSignKey())
                .compact();
    }

    public String createRefreshToken(String email, List<MemberRole> memberRoleList) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("roles", memberRoleList.stream().map(MemberRole::getName).collect(Collectors.toList()));

        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshTokenValidityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(getSignKey())
                .compact();
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build().parseClaimsJws(token);

            return claimsJws.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException exception) {
            throw new RuntimeException("JWT невалиден", exception);
        }
    }
}
