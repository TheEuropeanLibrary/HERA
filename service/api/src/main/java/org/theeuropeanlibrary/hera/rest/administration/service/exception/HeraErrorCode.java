package org.theeuropeanlibrary.hera.rest.administration.service.exception;


/**
 * Status codes used to as an error code in {@link HeraErrorMessage}.
 */
public enum HeraErrorCode {

	/** Security */
	ACCESS_DENIED_OR_OBJECT_DOES_NOT_EXIST_EXCEPTION,
    
    /** Datasets */
    DATASET_ALREADY_EXISTS,
    DATASET_NOT_EXISTS,
    
    /** Providers */
    PROVIDER_ALREADY_EXISTS,
    PROVIDER_NOT_EXISTS,
    
    /** Other */
    WRONG_CONTENT_RANGE,
    OTHER

}
