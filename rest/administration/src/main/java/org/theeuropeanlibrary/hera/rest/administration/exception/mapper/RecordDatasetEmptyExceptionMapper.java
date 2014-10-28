package org.theeuropeanlibrary.hera.rest.administration.exception.mapper;

import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.theeuropeanlibrary.hera.rest.administration.exception.DatasetEmptyException;

/**
 * RecordDatasetEmpty exception mapper
 */
@Provider
public class RecordDatasetEmptyExceptionMapper extends HeraExceptionMapper implements
		ExceptionMapper<DatasetEmptyException> {

}
