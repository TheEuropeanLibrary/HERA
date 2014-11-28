package org.theeuropeanlibrary.hera.rest.administration;

import static org.theeuropeanlibrary.hera.rest.administration.ParamConstants.P_PROVIDER;
import static org.theeuropeanlibrary.hera.rest.administration.ParamConstants.Q_FILTER;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.theeuropeanlibrary.hera.rest.administration.service.ProviderService;
import org.theeuropeanlibrary.hera.rest.administration.service.exception.ProviderAlreadyExistsException;
import org.theeuropeanlibrary.hera.rest.administration.service.exception.ProviderDoesNotExistException;
import org.theeuropeanlibrary.maia.common.AbstractEntity;
import org.theeuropeanlibrary.maia.common.definitions.Provider;
import org.theeuropeanlibrary.maia.common.filter.EntityFilter;
import org.theeuropeanlibrary.maia.common.filter.EntityFilterFactory;
import org.theeuropeanlibrary.maia.tel.model.provider.ProviderRegistry;

/**
 * Resource for DataProvider.
 * 
 * Get / Update / Delete provider
 * 
 */
@Path("/providers/{" + P_PROVIDER + "}")
@Component
@Scope("request")
public class ProviderResource {

	@Autowired
	private ProviderService<String> providerService;
	
	@Autowired
	private EntityFilterFactory<String, Provider<String>> filters;
	
    /**
     * @return Retrieves a provider.
     * @throws ProviderDoesNotExistException 
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getProvider(@PathParam(P_PROVIDER) String providerId, @QueryParam(Q_FILTER) String filterName) throws ProviderDoesNotExistException {

		Provider<String> p = providerService.getProvider(providerId);
		
		if (filterName != null) {
			EntityFilter<String, Provider<String>> f = filters.getFilterForName(filterName);
			p = (Provider<String>)  f.filter(p);
		}
		
        final Response response = Response.ok().entity(p).build();
        return response;
    }
    
	
    /**
     * Updates provider information.
     *
     * @param dataProviderProperties data provider properties.
     * @throws ProviderDoesNotExistException
     * @statuscode 200 object has been updated.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProvider(
    		@PathParam(P_PROVIDER) String providerId, Provider<String> provider, @QueryParam(Q_FILTER) String filterName)
    				throws ProviderAlreadyExistsException, ProviderDoesNotExistException {
    	
		if (filterName != null) {
	    	Provider<String> newProvider = provider;
			Provider<String> oldProvider = providerService.getProvider(providerId);

			EntityFilter<String, Provider<String>> f = filters.getFilterForName(filterName);
			f.merge(oldProvider, newProvider);
			
	    	providerService.updateProvider(providerId, oldProvider);
	        return Response.ok().build();
		}
    	
    	providerService.updateProvider(providerId, provider);
        return Response.ok().build();
    }
    
    @DELETE
    public Response deleteProvider(@PathParam(P_PROVIDER) String providerId) throws ProviderDoesNotExistException  {
    	
        providerService.deleteProvider(providerId);
        return Response.ok().build();
    }
}
