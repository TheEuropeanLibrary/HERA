package org.theeuropeanlibrary.hera.rest.administration.utils;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class ApplicationContextUtils implements ApplicationContextAware  {

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext)
            throws BeansException {
        ApplicationContextUtils.applicationContext = applicationContext;
    }
    
    @After
    public void clear() {
    	logoutEveryone();
    }

    public void login(String name, String password) {
        Authentication auth = new UsernamePasswordAuthenticationToken(name, password);
        SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(auth));
    }
    
    protected void logoutEveryone() {
        SecurityContextHolder.clearContext();
    } 
}
