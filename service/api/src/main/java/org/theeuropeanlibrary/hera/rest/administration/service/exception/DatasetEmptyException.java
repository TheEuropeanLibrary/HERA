package org.theeuropeanlibrary.hera.rest.administration.service.exception;


/**
 * This exception is thrown when the dataset is empty
 *
 */
public class DatasetEmptyException extends GenericException {

	public DatasetEmptyException(ErrorInfo e){
		super(e);
	}
	
	public DatasetEmptyException(IdentifierErrorInfo errorInfo) {
		super(errorInfo);
	}

	

}
