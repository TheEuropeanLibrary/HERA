package org.theeuropeanlibrary.hera.rest.administration;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

// TODO: should we move this to another project ?
/**
 * Login Resource 
 * 
 */
@Path("/login")
@Component
@Scope("request")
public class LoginResource {

    /**
     * @return Returns OK if the provided username + password combination 
     * represents a registered HERA user.
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @PreAuthorize("isAuthenticated()")
    public Response login() {
    	
    	final String username = SpringUserUtils.getUsername();
    	
    	List<String> roles = SpringUserUtils.getUserRoles();
    	ResultSet<String> result = new ResultSet<String>();
    	result.setResults(roles);

        final Response response = Response.ok().entity(result).build();
        return response;
    }
}
