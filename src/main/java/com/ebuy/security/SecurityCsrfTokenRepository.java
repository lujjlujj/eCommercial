package com.ebuy.security;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by terry on 2017/4/23.
 */
@Component
public class SecurityCsrfTokenRepository implements CsrfTokenRepository {

    public static final String CSRF_PARAMETER_NAME = "_csrf";

    public static final String CSRF_HEADER_NAME = "X-XSRF-TOKEN";

    private final Map<String, CsrfToken> tokenRepository = new ConcurrentHashMap<>();

    public SecurityCsrfTokenRepository() {
        //log.info("Creating {}", CustomCsrfTokenRepository.class.getSimpleName());
    }

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        return new DefaultCsrfToken(CSRF_HEADER_NAME, CSRF_PARAMETER_NAME, createNewToken());
    }

    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
        String key = getKey(request);
        System.out.println("Key:" + key + "token:"  + token.getToken());
        if (key == null)
            return;

        if (token == null) {
            tokenRepository.remove(key);
        } else {
            tokenRepository.put(key, token);
        }
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        String key = getKey(request);
        return key == null ? null : tokenRepository.get(key);
    }

    private String getKey(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    private String createNewToken() {
        return UUID.randomUUID().toString();
    }
}