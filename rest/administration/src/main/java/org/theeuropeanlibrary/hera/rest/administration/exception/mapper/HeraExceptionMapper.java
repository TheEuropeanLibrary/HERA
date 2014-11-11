package org.theeuropeanlibrary.hera.rest.administration.exception.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.theeuropeanlibrary.hera.rest.administration.service.exception.DatasetAlreadyExistsException;
import org.theeuropeanlibrary.hera.rest.administration.service.exception.DatasetDoesNotExistException;
import org.theeuropeanlibrary.hera.rest.administration.service.exception.HeraErrorCode;
import org.theeuropeanlibrary.hera.rest.administration.service.exception.HeraException;
import org.theeuropeanlibrary.hera.rest.administration.service.exception.ProviderAlreadyExistsException;
import org.theeuropeanlibrary.hera.rest.administration.service.exception.ProviderDoesNotExistException;

/**
 * Maps an {@link HeraException} together with an HTTP status code
 * and builds a response.
 */
public class HeraExceptionMapper {
	
    /**
     * Maps {@link DatasetAlreadyExistsException} to {@link Response}. Returns a response with HTTP status code 404 -
     * "Not Found" and a {@link ErrorInfo} with exception details as a message body.
     * 
     * @param exception
     *            the exception to map to a response
     * @return a response mapped from the supplied exception
     */
    public Response toResponse(DatasetAlreadyExistsException exception) {
        return buildResponse(Response.Status.NOT_FOUND, HeraErrorCode.DATASET_ALREADY_EXISTS, exception);
    }
	
    /**
     * Maps {@link ProviderAlreadyExistsException} to {@link Response}. Returns a response with HTTP status code 404 -
     * "Not Found" and a {@link ErrorInfo} with exception details as a message body.
     * 
     * @param exception
     *            the exception to map to a response
     * @return a response mapped from the supplied exception
     */
    public Response toResponse(ProviderAlreadyExistsException exception) {
        return buildResponse(Response.Status.NOT_FOUND, HeraErrorCode.PROVIDER_ALREADY_EXISTS, exception);
    }
	
    /**
     * Maps {@link DatasetDoesNotExistException} to {@link Response}. Returns a response with HTTP status code 404 -
     * "Not Found" and a {@link ErrorInfo} with exception details as a message body.
     * 
     * @param exception
     *            the exception to map to a response
     * @return a response mapped from the supplied exception
     */
    public Response toResponse(DatasetDoesNotExistException exception) {
        return buildResponse(Response.Status.NOT_FOUND, HeraErrorCode.DATASET_NOT_EXISTS, exception);
    }
	
    /**
     * Maps {@link ProviderNotExistsException} to {@link Response}. Returns a response with HTTP status code 404 -
     * "Not Found" and a {@link ErrorInfo} with exception details as a message body.
     * 
     * @param exception
     *            the exception to map to a response
     * @return a response mapped from the supplied exception
     */
    public Response toResponse(ProviderDoesNotExistException exception) {
        return buildResponse(Response.Status.NOT_FOUND, HeraErrorCode.PROVIDER_NOT_EXISTS, exception);
    }

    private static Response buildResponse(Response.Status httpStatus, HeraErrorCode errorCode, Exception e) {
        return buildResponse(httpStatus.getStatusCode(), errorCode, e);
    }

    private static Response buildResponse(int httpStatusCode, HeraErrorCode errorCode, Exception e) {
        return Response.status(httpStatusCode).type(MediaType.APPLICATION_XML).entity(new HeraErrorMessage(errorCode.name(), e.getMessage())).build();
    }
}
