package org.theeuropeanlibrary.hera.rest.administration;

import static org.theeuropeanlibrary.hera.rest.administration.ParamConstants.Q_START_FROM;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.theeuropeanlibrary.hera.rest.administration.service.ProviderService;
import org.theeuropeanlibrary.hera.rest.administration.service.exception.ProviderAlreadyExistsException;
import org.theeuropeanlibrary.maia.common.definitions.Provider;

/**
 * Resource for Providers.
 * 
 * Get / Create provider
 *
 */
@Path("/providers")
@Component
@Scope("request")
public class ProvidersResource {

    @Autowired
    private ProviderService<String> providerService;

    private static final int NUMBER_OF_ELEMENTS_ON_PAGE = 100;

    /**
     * Lists all providers. Result is returned in slices.
     *
     * @param startFrom reference to next slice of result.
     * @return slice of result.
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public ResultSlice<Provider<String>> getProviders(@QueryParam(Q_START_FROM) String startFrom) {
        ResultSlice<Provider<String>> pList = new ResultSlice<>();
        pList.setResults(providerService.getProviders(startFrom, NUMBER_OF_ELEMENTS_ON_PAGE));
        return pList;
    }

    /**
     * @return A new provider including his id.
     *
     * @param provider to create.
     * @throws ProviderAlreadyExistsException provider already * exists.
     * @statuscode 200 object has been created.
     */
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @PreAuthorize("isAuthenticated()")
    @POST
    public Response createProvider(Provider<String> provider)
            throws ProviderAlreadyExistsException {
    	
    	Provider<String> createdProvider = providerService.createProvider(provider);
    	return Response.ok().entity(createdProvider).build();
    }
}
