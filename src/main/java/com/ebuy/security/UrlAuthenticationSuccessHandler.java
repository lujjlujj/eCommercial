package com.ebuy.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by terry on 2017/4/23.
 */
public class UrlAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("Success to auth.");
        String requestType = request.getHeader("x-requested-with");
        if (!StringUtils.isEmpty(requestType)) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().print("{\"success\": \"true\"}");
        } else {
            setDefaultTargetUrl("/");
            setAlwaysUseDefaultTargetUrl(true);
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
