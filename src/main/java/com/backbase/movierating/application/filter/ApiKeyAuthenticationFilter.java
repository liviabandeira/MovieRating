package com.backbase.movierating.application.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

public class ApiKeyAuthenticationFilter implements Filter {

    @Value("${backbase.api.key.header}")
    private static String API_KEY_HEADER;

    @Value("${backbase.api.key.value}")
    private static String API_KEY_VALUE;
    //Todo: Use env vars

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        if(request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            String apiKey = getApiKey((HttpServletRequest) request);
            if(apiKey != null) {
                if(apiKey.equals("test")) {
                    ApiKeyAuthenticationToken apiToken = new ApiKeyAuthenticationToken(apiKey, AuthorityUtils.NO_AUTHORITIES);
                    SecurityContextHolder.getContext().setAuthentication(apiToken);
                } else {
                    HttpServletResponse httpResponse = (HttpServletResponse) response;
                    httpResponse.setStatus(401);
                    httpResponse.getWriter().write("Invalid API Key");
                    return;
                }
            }
        }

        chain.doFilter(request, response);

    }

    private String getApiKey(HttpServletRequest httpRequest) {
        String apiKey = null;

        String authHeader = httpRequest.getHeader("X-Backbase-Api-Key");
        if(authHeader != null) {
            apiKey = authHeader.trim();
        }

        return apiKey;
    }
}