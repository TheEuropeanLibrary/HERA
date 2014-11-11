package org.theeuropeanlibrary.hera.rest.administration.service.exception;


/**
 * Exception thrown when a dataset already exists in the database 
 *
 */
public class DatasetAlreadyExistsException extends HeraException {

	public DatasetAlreadyExistsException(String errorMessage) {
		super(errorMessage);
	}

	public DatasetAlreadyExistsException() {
		super();
	}
}
