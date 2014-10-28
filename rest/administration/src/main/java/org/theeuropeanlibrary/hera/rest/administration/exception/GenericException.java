package org.theeuropeanlibrary.hera.rest.administration.exception;


/**
 * Generic Exception
 */
public class GenericException extends Exception {
    
    private final IdentifierErrorInfo errorInfo;

    public GenericException(ErrorInfo e) {
        super(e.getDetails());
        errorInfo=null;
    }

    public GenericException(IdentifierErrorInfo errorInfo) {
        super(errorInfo.getErrorInfo().getDetails());
        this.errorInfo = errorInfo;
    }

    public IdentifierErrorInfo getErrorInfo() {
        return this.errorInfo;
    }
}
