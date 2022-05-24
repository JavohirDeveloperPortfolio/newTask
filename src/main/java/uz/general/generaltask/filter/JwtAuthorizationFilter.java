package uz.general.generaltask.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import uz.general.generaltask.utils.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //if user try to authenticate, there is no need to authorize it
        if (request.getServletPath().equals("/swagger-ui.html") || request.getServletPath().equals("/api/user/signUp") || request.getServletPath().equals("/api/login") || request.getServletPath().equals("/api/user/refresh")){
            filterChain.doFilter(request, response);
            return;
        }

        String token = JwtUtil.getTokenFromRequestHeader(request, response);

        //checking validation
        DecodedJWT decodedJWT = JwtUtil.validateToken(token, response);
        if (decodedJWT == null){
            return;
        }

        String username = decodedJWT.getSubject();
        String[] roles = decodedJWT.getClaim("roles").asArray(String.class);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                username,
                null,
                Arrays.stream(roles).map(SimpleGrantedAuthority::new).toList()
        ));

        filterChain.doFilter(request, response);
    }
}
