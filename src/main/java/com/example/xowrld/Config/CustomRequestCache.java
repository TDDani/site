package com.example.xowrld.Config;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomRequestCache extends HttpSessionRequestCache {

    @Override
    public void saveRequest(HttpServletRequest request, HttpServletResponse response) {
        String requestUrl = request.getRequestURI();
        if (!requestUrl.contains(".")) { // filter out file paths and resources
            super.saveRequest(request, response);
        }
    }
}