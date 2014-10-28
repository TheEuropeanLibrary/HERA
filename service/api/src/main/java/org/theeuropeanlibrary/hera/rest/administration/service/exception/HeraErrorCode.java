package org.theeuropeanlibrary.hera.rest.administration.service.exception;


/**
 * Status codes used to as an error code in {@link ErrorInfo}.
 */
public enum HeraErrorCode {

	ACCESS_DENIED_OR_OBJECT_DOES_NOT_EXIST_EXCEPTION,
    CANNOT_MODIFY_PERSISTENT_REPRESENTATION,
    DATASET_ALREADY_EXISTS,
    DATASET_NOT_EXISTS,
    PROVIDER_NOT_EXISTS,
    WRONG_CONTENT_RANGE,
    OTHER

}
