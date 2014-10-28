package org.theeuropeanlibrary.hera.rest.administration.exception;


/**
 * Exception thrown when a dataset already exists in the database 
 *
 */
public class DatasetAlreadyExistsException extends GenericException {

	public DatasetAlreadyExistsException(ErrorInfo errorInfo) {
		super(errorInfo);
	}

	public DatasetAlreadyExistsException(IdentifierErrorInfo errorInfo) {
		super(errorInfo);
	}

	

}
