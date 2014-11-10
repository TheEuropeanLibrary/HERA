package org.theeuropeanlibrary.hera.rest.administration.exception.mapper;

import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.theeuropeanlibrary.hera.rest.administration.service.exception.DatasetDoesNotExistException;

/**
 * RecordIdDoesNotExist exception mapper
 */
@Provider
public class RecordIdDoesNotExistExceptionMapper extends HeraExceptionMapper implements
		ExceptionMapper<DatasetDoesNotExistException> {

}
