package org.theeuropeanlibrary.hera.rest.administration.service.exception;


/**
 * Exception thrown when trying to access a dataset that does not exist
 */
public class DatasetDoesNotExistException extends HeraException {

	public DatasetDoesNotExistException(final String errorMessage) {
		super(errorMessage);
	}
	
	public DatasetDoesNotExistException() {
		super();
	}
}
