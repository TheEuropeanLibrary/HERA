package org.theeuropeanlibrary.hera.rest.administration.service.exception;


/**
 * Thrown when there is attempt to access a provider that does not exist.
 */
public class ProviderDoesNotExistException extends HeraException {

	public ProviderDoesNotExistException(final String errorMessage){
		super(errorMessage);
	}
	
    public ProviderDoesNotExistException() {
    }
}
