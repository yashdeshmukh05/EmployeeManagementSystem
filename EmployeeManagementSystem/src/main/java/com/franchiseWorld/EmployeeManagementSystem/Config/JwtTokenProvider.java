package com.franchiseWorld.EmployeeManagementSystem.Config;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
@Service
public class JwtTokenProvider {

//    A key used to sign and verify JWTs.
//    This key is initialized using JwtConstant.SECRET_KEY, which is a secret key (stored as a constant string) and is converted into bytes via .getBytes().
//    Then it’s passed to hmacShaKeyFor() to create an HMAC SHA-256-based key.
    private SecretKey key=Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    //This methode generate JWT Tokens
    public String generateToken(Authentication auth) {
        //Retrieves the collection of roles or permissions (GrantedAuthority) assigned to the user.
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

        String roles = populateAuthorities(authorities);

        //Creating JWT Tokens
        String jwt= Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+86400000))
                .claim("email",auth.getName())
                .claim("authorities", roles)
                .signWith(key)
                .compact();

        return jwt;
    }

    //This method extracts the email from the provided JWT.
    public String getEmailFromJwtToken(String jwt) {
        jwt=jwt.substring(7);       //to extract bearer keyword we use 7

        //Parsing the JWT to Get Claims
        Claims claims=Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        String email=String.valueOf(claims.get("email"));

        return email;
    }

    //This method for extracting roles
    public String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> auths=new HashSet<>();

        for(GrantedAuthority authority:collection) {
            auths.add(authority.getAuthority()); //.getAuthority(): Retrieves the string representation of each role/authority.

        }
        return String.join(",",auths);
    }

}
