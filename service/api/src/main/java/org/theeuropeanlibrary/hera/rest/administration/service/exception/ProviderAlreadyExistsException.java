package org.theeuropeanlibrary.hera.rest.administration.service.exception;


/**
 * Thrown on attempt to create a provider that already exists.
 */
public class ProviderAlreadyExistsException extends HeraException {

	public ProviderAlreadyExistsException(final String errorMessage){
		super(errorMessage);
	}
	
    public ProviderAlreadyExistsException() {
        super();
    }
}
