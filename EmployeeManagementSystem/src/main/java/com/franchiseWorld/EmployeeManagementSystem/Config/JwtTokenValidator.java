package com.franchiseWorld.EmployeeManagementSystem.Config;

import java.io.IOException;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenValidator extends OncePerRequestFilter { // OncePerRequestFilter is responsible for validating JWT tken attached to each incoming request


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //This method is called for every incoming HTTP request.
        //It contains the core logic for validating the JWT and setting up the security context.

        //Extracting the JWT from Request Headers
        String jwt = request.getHeader(JwtConstant.JWT_HEADER);
        System.out.println("jwt ------ "+jwt);
        if(jwt!=null) {
            jwt=jwt.substring(7);
            System.out.println("jwt ------ "+jwt);
            try {


                //JWT Validation and Parsing
                SecretKey key= Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

                Claims claims= Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();


                //Extracting Claims (Email and Authorities)
                String email=String.valueOf(claims.get("email"));

                String authorities=String.valueOf(claims.get("authorities"));

                //Converting Authorities and Creating Authentication Object
                List<GrantedAuthority> auths= AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
                Authentication athentication=new UsernamePasswordAuthenticationToken(email,null, auths);

                //Setting Authentication in Security Context
                SecurityContextHolder.getContext().setAuthentication(athentication);

            } catch (Exception e) {
                // TODO: handle exception
                throw new BadCredentialsException("invalid token...");
            }
        }
        filterChain.doFilter(request, response);

    }

}


