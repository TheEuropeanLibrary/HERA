package org.theeuropeanlibrary.hera.rest.administration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

// TODO: Move somewhere else
/**
 * Utility class containing function for spring security usage.
 * 
 * @author Markus Muhr (markus.muhr@theeuropeanlibrary.org)
 */
public final class SpringUserUtils {
	
    /**
     * @return Name of the currently logged in user
     */
    public static String getUsername() {
        String username = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            if (auth.getPrincipal() instanceof UserDetails) {
                username = ((UserDetails) auth.getPrincipal()).getUsername();
            } else {
                username = auth.getPrincipal().toString();
            }
        }
        return username;
    }
    
    /**
     * @return Spring Authorites for the currently logged in user
     */
    public static Collection<? extends GrantedAuthority> getUserAuthorities() {
    	
        Collection<? extends GrantedAuthority> roles = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (auth != null) {
            if (auth.getPrincipal() instanceof UserDetails) {
            	roles = ((UserDetails) auth.getPrincipal()).getAuthorities();
            }
        }
        return roles;
    }
    
    /**
     * @return Roles (as Strings) for the currently logged in user
     */
    public static List<String> getUserRoles() {

        Collection<? extends GrantedAuthority> roles = getUserAuthorities();
        List<String> rolesAsStrings = new ArrayList();
        
    	for (GrantedAuthority a: roles) {
    		rolesAsStrings.add(a.getAuthority());
    	}
        return rolesAsStrings;
    }
	
}
