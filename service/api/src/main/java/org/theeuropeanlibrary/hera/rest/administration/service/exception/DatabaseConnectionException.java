package org.theeuropeanlibrary.hera.rest.administration.service.exception;


/**
 * Database connection exception
 */
public class DatabaseConnectionException extends HeraException{

	public DatabaseConnectionException(String errorMessage){
		super(errorMessage);
	}
	
	public DatabaseConnectionException() {
		super();
	}
}
