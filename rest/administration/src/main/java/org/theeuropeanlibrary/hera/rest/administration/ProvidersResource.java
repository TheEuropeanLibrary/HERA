package org.theeuropeanlibrary.hera.rest.administration;

import static org.theeuropeanlibrary.hera.rest.administration.ParamConstants.P_PROVIDER;
import static org.theeuropeanlibrary.hera.rest.administration.ParamConstants.Q_START_FROM;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.theeuropeanlibrary.hera.rest.administration.exception.ProviderAlreadyExistsException;
import org.theeuropeanlibrary.hera.rest.administration.service.ProviderService;
import org.theeuropeanlibrary.maia.common.definitions.Provider;

/**
 * Resource for DataProviders.
 * 
 */
@Path("/providers")
@Component
@Scope("request")
public class ProvidersResource {

	@Autowired(required = false)
	private ProviderService providerService;
	
	private static final int NUMBER_OF_ELEMENTS_ON_PAGE = 100;

	/**
	 * Lists all providers. Result is returned in slices.
	 * 
	 * @param startFrom reference to next slice of result.
	 * @return slice of result.
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public ResultSlice<Provider> getProviders(@QueryParam(Q_START_FROM) String startFrom) {
		return providerService.getProviders(startFrom, NUMBER_OF_ELEMENTS_ON_PAGE);
	}

	/**
	 * Creates a new data provider. Response contains uri to created resource in
	 * as content location.
	 * 
	 * @param dataProviderProperties data provider properties.
	 * @param providerId data provider id (required)
	 * @return URI to created resource in content location
	 * @throws ProviderAlreadyExistsException provider already * exists.
	 * @statuscode 201 object has been created.
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@PreAuthorize("isAuthenticated()")
	public Response createProvider(@Context UriInfo uriInfo, @QueryParam(P_PROVIDER) String providerId)
			throws ProviderAlreadyExistsException {
		
		Provider provider = providerService.createProvider(providerId, "");

		URI providerURI = null;
		try {
			providerURI = new URI("");
		} catch (URISyntaxException e) {
		}
		return Response.created(providerURI).build();
	}
}
