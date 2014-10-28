package org.theeuropeanlibrary.hera.rest.administration.exception.mapper;

import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.theeuropeanlibrary.hera.rest.administration.exception.ProviderDoesNotExistException;

/**
 * ProviderDoesNotExistException exception mapper
 */
@Provider
public class ProviderDoesNotExistExceptionMapper extends HeraExceptionMapper implements
		ExceptionMapper<ProviderDoesNotExistException> {

}
