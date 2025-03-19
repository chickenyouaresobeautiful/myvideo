package com.daiweij.myvedio.filter;

import com.daiweij.myvedio.common.utils.JwtUtils;
import com.daiweij.myvedio.common.utils.LogUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@WebFilter
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = getJwtFromRequest(request);
        LogUtil.info(this.getClass(), "doFilterInternal token: " + token);

        if (token != null && validateToken(token)) {
            Claims claims = JwtUtils.parseToken(token);
            String username = claims.getSubject();

            // 将用户名存储到 SecurityContext
            SecurityContextHolder.getContext().setAuthentication(new JwtAuthenticationToken(username));
        }

        filterChain.doFilter(request, response);  // 继续过滤链
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);  // 去掉 "Bearer " 前缀
        }
        return null;
    }

    private boolean validateToken(String token) {
        try {
            return JwtUtils.validateToken(token);
        } catch (Exception e) {
            return false;
        }
    }
}
