package org.theeuropeanlibrary.hera.rest.administration.exception.mapper;

import javax.ws.rs.core.Response;

import org.theeuropeanlibrary.hera.rest.administration.service.exception.DatabaseConnectionException;
import org.theeuropeanlibrary.hera.rest.administration.service.exception.DatasetAlreadyExistsException;
import org.theeuropeanlibrary.hera.rest.administration.service.exception.DatasetDoesNotExistException;
import org.theeuropeanlibrary.hera.rest.administration.service.exception.GenericException;
import org.theeuropeanlibrary.hera.rest.administration.service.exception.ProviderAlreadyExistsException;
import org.theeuropeanlibrary.hera.rest.administration.service.exception.ProviderDoesNotExistException;

/**
 * Generic class exposing the exceptions
 */
public class HeraExceptionMapper {

	/**
	 * @param e A {@link CloudIdDoesNotExistException}
	 * @return An API exception response corresponding to the exception
	 */
	public Response toResponse(ProviderDoesNotExistException e){
		return buildResponse(e);
	}
	/**
	 * @param e A {@link DatabaseConnectionException}
	 * @return An API exception response corresponding to the exception
	 */
	public Response toResponse(DatabaseConnectionException e){
		return buildResponse(e);
	}
	/**
	 * @param e A {@link DatasetAlreadyExistsException}
	 * @return An API exception response corresponding to the exception
	 */
	public Response toResponse(DatasetAlreadyExistsException e){
		return buildResponse(e);
	}
	/**
	 * @param e A {@link DatasetDoesNotExistException}
	 * @return An API exception response corresponding to the exception
	 */
	public Response toResponse(DatasetDoesNotExistException e){
		return buildResponse(e);
	}
	
	/**
	 * @param e A {@link ProviderAlreadyExistsException}
	 * @return An API exception response corresponding to the exception
	 */
	public Response toResponse(ProviderAlreadyExistsException e){
		return buildResponse(e);
	}
	private Response buildResponse(GenericException e){
		return Response.status(e.getErrorInfo().getHttpCode()).entity(e.getErrorInfo().getErrorInfo()).build();
	}
}
