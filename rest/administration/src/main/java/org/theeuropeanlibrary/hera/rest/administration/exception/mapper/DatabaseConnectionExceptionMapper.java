package org.theeuropeanlibrary.hera.rest.administration.exception.mapper;

import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.theeuropeanlibrary.hera.rest.administration.exception.DatabaseConnectionException;

/**
 * DatabaseConnection exception mapper
 */
@Provider
public class DatabaseConnectionExceptionMapper extends HeraExceptionMapper implements
		ExceptionMapper<DatabaseConnectionException> {

}
