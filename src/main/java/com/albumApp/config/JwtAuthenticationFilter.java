package com.albumApp.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            if (jwt != null) {
                logger.info("JWT Token found: " + jwt);
                String username = jwtUtils.extractUsername(jwt);
                logger.info("Extracted username from token: " + username);
                
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                logger.info("Loaded user details for: " + userDetails.getUsername());
                
                if (jwtUtils.validateToken(jwt, userDetails)) {
                    logger.info("Token validated successfully");
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    logger.info("Authentication set in SecurityContext");
                } else {
                    logger.error("Token validation failed");
                }
            } else {
                logger.warn("No JWT token found in request");
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
            logger.error("Stack trace: ", e);
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        logger.info("Authorization header: " + headerAuth);

        if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }
} 