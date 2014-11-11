package org.theeuropeanlibrary.hera.rest.administration.exception.mapper;

import javax.xml.bind.annotation.XmlRootElement;

import org.joda.time.DateTime;

/**
 * Contains error information specific to Hera internal services.
 * 
 * Sent together with a rest response in case of error.
 * 
 * @see HeraExceptionMapper
 */
@XmlRootElement
public class HeraErrorMessage {

	/**
	 * Error code specific to the error occured.
	 */
    private String errorCode;
	
	/**
	 * Details message for error.
	 */
	private String details;

	/** Time of creation */
	private DateTime createdAt = DateTime.now();

	public HeraErrorMessage() {
	}

	public HeraErrorMessage(String details) {
		this.details = details;
	}
	
	public HeraErrorMessage(String errorCode, String details) {
		this.errorCode = errorCode;
		this.details = details;
	}

	public String getDetails() {
		return details;
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public DateTime getCreatedAt() {
		return createdAt;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}
