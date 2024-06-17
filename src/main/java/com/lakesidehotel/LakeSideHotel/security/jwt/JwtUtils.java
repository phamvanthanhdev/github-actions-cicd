package com.lakesidehotel.LakeSideHotel.security.jwt;

import com.lakesidehotel.LakeSideHotel.security.user.HotelUserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUtils {
    private static  final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    @Value("${security.jwt.secret}")
    private String jwtSecret;
    @Value("${security.jwt.jwtExpirationTime}")
    private int jwtExpirationTime;

    public String generateJwtTokenForUser(Authentication authentication){
        HotelUserDetails userPricipal = (HotelUserDetails) authentication.getPrincipal();
        List<String> roles = userPricipal.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority).toList();
        return Jwts.builder()
                .setSubject(userPricipal.getUsername())
                .claim("role", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime()+jwtExpirationTime))
                .signWith(key(), SignatureAlgorithm.HS256).compact();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUserNameFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJwt(token).getBody().getSubject();
    }
}
