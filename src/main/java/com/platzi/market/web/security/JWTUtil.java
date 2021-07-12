package com.platzi.market.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JWTUtil {
    private static final String KEY = "PL4TZ1";//La llave con la que se va a firmar el token, la llave deberia ser mas compleja

    public String generateToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername())//el nombre del usuario
                .setIssuedAt(new Date())//la fecha en el que se creo el token
                .setExpiration(new Date(System.currentTimeMillis() + 1000 *60 *60 *10 )) //la fecha de expiracion del token en 10 horas
                .signWith(SignatureAlgorithm.HS256, KEY)//para firmar el metodo el primer parametro, para firmar el algorimo y el web token segundo parametro
                .compact();
    }

    public  boolean validateToken(String token, UserDetails user){
        return user.getUsername().equals(extractUsername(token)) &&
                !isTokenExpired(token);
    }

    public String extractUsername(String token){
        return getClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token){
        return getClaims(token).getExpiration().before(new Date());//si esta antes de la fecha actual retorna true, de lo contrario un false
    }


    public Claims getClaims(String token){
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }
}
