package org.theeuropeanlibrary.hera.rest.administration.exception;


/**
 * Database connection exception
 */
public class DatabaseConnectionException extends GenericException{

	public DatabaseConnectionException(ErrorInfo e){
		super(e);
	}
	
	public DatabaseConnectionException(IdentifierErrorInfo errorInfo) {
		super(errorInfo);
	}

	

}
