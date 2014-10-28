package org.theeuropeanlibrary.hera.rest.administration.exception;

import javax.xml.bind.annotation.XmlRootElement;

import org.joda.time.DateTime;

/**
 * Rest response that is returned if some error occurred.
 */
@XmlRootElement
public class ErrorInfo {

	/**
	 * Code of error. This is specific for a particular rest api.
	 */
	private String errorCode;

	/**
	 * Details message for error.
	 */
	private String details;

	/** Time of creation */
	private DateTime createdAt = DateTime.now();

	public ErrorInfo() {
	}

	public ErrorInfo(String errorCode, String details) {
		this.errorCode = errorCode;
		this.details = details;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getDetails() {
		return details;
	}

	public DateTime getCreatedAt() {
		return createdAt;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}
