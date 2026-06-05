package com.chaitanya.SecurityImpl03.Handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
{
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException
    {
        boolean isAdmin = authentication.getAuthorities()
                .stream().anyMatch(granted->granted.getAuthority().equals("ROLE_ADMIN"));

        if(isAdmin)
        {
            setDefaultTargetUrl("/admin/home");
        }
        else
        {
            setDefaultTargetUrl("/user/home");
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
