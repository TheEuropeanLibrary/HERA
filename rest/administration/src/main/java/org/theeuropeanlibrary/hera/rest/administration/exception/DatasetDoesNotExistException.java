package org.theeuropeanlibrary.hera.rest.administration.exception;


/**
 * Exception thrown when trying to access a dataset that does not exist
 *
 */
public class DatasetDoesNotExistException extends GenericException {

	public DatasetDoesNotExistException(ErrorInfo errorInfo) {
		super(errorInfo);
	}
	
	public DatasetDoesNotExistException(IdentifierErrorInfo errorInfo) {
		super(errorInfo);
	}
}
