package org.theeuropeanlibrary.hera.rest.administration;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.theeuropeanlibrary.maia.common.model.DataProvider;

/**
 * Resource for DataProvider.
 *
 * @author Emmanouil Koufakis (emmanouil.koufakis@kb.nl)
 * @since 17.10.2014
 */
@Path("/data-providers/{providerId}")
@Component
@Scope("request")
public class DataProviderResource {

    /**
     * @param providerId which provider should be retrieved
     * @return provider as response
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
//     @PreAuthorize("isAuthenticated()")
    public Response getProvider(@PathParam("providerId") String providerId) {
        final String username = DataProviderResource.getUsername();
        if (username.equals("Alina")) {
            DataProvider p = new DataProvider(providerId);
            p.setName("Alina");
            final Response response = Response.ok().entity(p).build();
            return response;
        }

        DataProvider p = new DataProvider(providerId);
        p.setName("Manos");

        final Response response = Response.ok().entity(p).build();
        return response;
    }

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
}
