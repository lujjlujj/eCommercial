package com.ebuy.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by terry on 2017/4/23.
 */
public class UrlAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String requestType = request.getHeader("x-requested-with");
        if (!StringUtils.isEmpty(requestType)) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().print("{\"success\": \"false\"}");
        } else {
            setDefaultFailureUrl("/security/login?error=true");
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
