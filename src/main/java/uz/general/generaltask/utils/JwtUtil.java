package uz.general.generaltask.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import uz.general.generaltask.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public abstract class JwtUtil {

    public static String generateAccessToken(User user){
        long accessTokenExpireTime = 1000 * 60 * 15L; //15 minutes
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+ accessTokenExpireTime))
                .withClaim(
                        "roles",
                        user
                                .getAuthorities()
                                .stream()
                                .map(GrantedAuthority::getAuthority)
                                .toList()
                )
                .sign(getAlgorithm());
    }

    public static String generateRefreshToken(User user){
        long refreshTokenExpireTime = 1000 * 60 * 60 * 24 * 30L; //30 days
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+ refreshTokenExpireTime))
                .sign(getAlgorithm());
    }

    public static DecodedJWT validateToken(String token, HttpServletResponse response) throws IOException {
        try {
            JWTVerifier jwtVerifier = JWT.require(getAlgorithm()).build();
            return jwtVerifier.verify(token);
        }
        catch (Exception e){
            response.setStatus(HttpStatus.FORBIDDEN.value());
            new ObjectMapper().writeValue(response.getOutputStream(), e.getMessage());
            return null;
        }
    }

    public static Algorithm getAlgorithm(){
        return Algorithm.HMAC256("Secret key".getBytes(StandardCharsets.UTF_8));
    }

    public static String getTokenFromRequestHeader(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        //checking header is null or not to start with Bearer
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
            response.setStatus(HttpStatus.FORBIDDEN.value());
            new ObjectMapper().writeValue(response.getOutputStream(), "Token is not found");
            return null;
        }

        return authorizationHeader.substring("Bearer ".length());
    }
}
