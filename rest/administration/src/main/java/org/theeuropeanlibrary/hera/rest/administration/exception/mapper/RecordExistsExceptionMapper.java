package org.theeuropeanlibrary.hera.rest.administration.exception.mapper;

import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.theeuropeanlibrary.hera.rest.administration.exception.DatasetAlreadyExistsException;

/**
 * RecordExists exception mapper
 */
@Provider
public class RecordExistsExceptionMapper extends HeraExceptionMapper implements ExceptionMapper<DatasetAlreadyExistsException> {

}
