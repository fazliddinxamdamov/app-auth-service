package com.fazliddin.appauthservice.security;

import com.fazliddin.appauthservice.service.AuthService;
import com.google.gson.Gson;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final AuthService authService;
    private final Gson gson;

    public JwtFilter(JwtProvider jwtProvider, @Lazy AuthService authService, Gson gson) {
        this.jwtProvider = jwtProvider;
        this.authService = authService;
        this.gson = gson;
    }

    private final Map<String, String> clients = Map.of(
            "gatewayServiceUsername", "gatewayServicePassword",
            "orderServiceUsername", "orderServicePassword",
            "productServiceUsername", "productServicePassword",
            "branchServiceUsername", "branchServicePassword"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String requestUsername = request.getHeader("serviceUsername");
        String requestPassword = request.getHeader("servicePassword");

//        if (!checkUsernameAndPassword(requestUsername, requestPassword)) {
//            ApiResult<Object> apiResult = new ApiResult<>(false, List.of(
//                    new ErrorData(MessageService.getMessage("FORBIDDEN"), 403)));
//
//            response.getWriter().write(gson.toJson(apiResult));
//            response.setStatus(403);
//            response.setContentType("application/json");
//            return;
//        }

        String authorization = request.getHeader("Authorization");

        if (authorization != null && authorization.startsWith("Bearer")) {
            authorization = authorization.substring(7);
            String username = jwtProvider.getUsername(authorization);
            UserDetails userDetails = authService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request, response);
    }


    private boolean checkUsernameAndPassword(String requestUsername, String requestPassword) {
        try {
            String password = clients.get(requestUsername);
            return Objects.equals(requestPassword, password);
        } catch (Exception e) {
            return false;
        }
    }

}
