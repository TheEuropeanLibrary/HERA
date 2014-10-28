package org.theeuropeanlibrary.hera.rest.administration.exception.mapper;

import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.theeuropeanlibrary.hera.rest.administration.exception.ProviderAlreadyExistsException;

/**
 * Provider Already Exists exception mapper
 */
@Provider
public class ProviderAlreadyExistsExceptionMapper extends HeraExceptionMapper implements
        ExceptionMapper<ProviderAlreadyExistsException> {
}
