package org.theeuropeanlibrary.hera.rest.administration.exception;


/**
 * Thrown when there is attempt to access a provider that does not exist.
 */
public class ProviderDoesNotExistException extends GenericException {

	public ProviderDoesNotExistException(ErrorInfo e){
		super(e);
	}
	
    public ProviderDoesNotExistException(IdentifierErrorInfo errorInfo) {
        super(errorInfo);
    }
}
