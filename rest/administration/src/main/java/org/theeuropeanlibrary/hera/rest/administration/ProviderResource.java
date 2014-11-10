package org.theeuropeanlibrary.hera.rest.administration;

import static org.theeuropeanlibrary.hera.rest.administration.ParamConstants.P_PROVIDER;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.theeuropeanlibrary.hera.rest.administration.service.ProviderService;
import org.theeuropeanlibrary.hera.rest.administration.service.exception.ProviderAlreadyExistsException;
import org.theeuropeanlibrary.hera.rest.administration.service.exception.ProviderDoesNotExistException;
import org.theeuropeanlibrary.maia.common.definitions.Provider;
import org.theeuropeanlibrary.maia.tel.model.provider.ProviderConstants;

/**
 * Resource for DataProvider.
 * 
 */
@Path("/providers/{" + P_PROVIDER + "}")
@Component
@Scope("request")
public class ProviderResource {

	@Autowired(required = false)
	private ProviderService providerService;
	
    /**
     * @return Retrieves a data-provider.
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
//     @PreAuthorize("isAuthenticated()")
    public Response getProvider(@PathParam(P_PROVIDER) String providerId) {
    	
    	// TODO: replace this dummy example
    	String username = SpringUserUtils.getUsername();
    	if (username.equals("Alina")) {

    		Provider p = new Provider();
    		p.setId(providerId);
    		p.addValue(ProviderConstants.NAME, "Alina");
    		
            final Response response = Response.ok().entity(p).build();
            return response;
    	}
		Provider p = new Provider();
		p.setId(providerId);
		p.addValue(ProviderConstants.NAME, "manos");
        final Response response = Response.ok().entity(p).build();
        return response;
    }
    
    /**
     * Updates data provider information.
     *
     * @param dataProviderProperties data provider properties.
     * @throws ProviderDoesNotExistException
     * @statuscode 204 object has been updated.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public void updateProvider(String dataProviderProperties,
    		@PathParam(P_PROVIDER) String providerId,
    		@Context UriInfo uriInfo)
            throws ProviderAlreadyExistsException {
    	
//        Provider provider = providerService.updateProvider(providerId, dataProviderProperties);
    }
    
    @DELETE
    public void deleteProvider(@PathParam(P_PROVIDER) String providerId)  {
        providerService.deleteProvider(providerId);
    }
}
