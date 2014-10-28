package org.theeuropeanlibrary.hera.rest.administration.exception;


/**
 * Thrown when there is attempt to create a provider that already exists.
 */
public class ProviderAlreadyExistsException extends GenericException {

	public ProviderAlreadyExistsException(ErrorInfo e){
		super(e);
	}
	
    public ProviderAlreadyExistsException(IdentifierErrorInfo errorInfo) {
        super(errorInfo);
    }
}
