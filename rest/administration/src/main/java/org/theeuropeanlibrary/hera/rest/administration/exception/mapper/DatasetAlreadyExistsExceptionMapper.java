package org.theeuropeanlibrary.hera.rest.administration.exception.mapper;

import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.theeuropeanlibrary.hera.rest.administration.service.exception.DatasetAlreadyExistsException;

/**
 * Provider Already Exists exception mapper
 */
@Provider
public class DatasetAlreadyExistsExceptionMapper extends HeraExceptionMapper implements
        ExceptionMapper<DatasetAlreadyExistsException> {
}
