package org.theeuropeanlibrary.hera.rest.administration;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.theeuropeanlibrary.hera.rest.administration.exception.mapper.HeraErrorMessage;

/**
 * Util for parameters.
 */
final class ParamUtil {

    /**
     * Checks if parameter value is not null. If it is, WebApplicationException is thrown with 400 HTTP code and
     * suitable message in response body indicating name of form parameter name that is required.
     * 
     * @param parameterName
     *            form parameter name
     * @param parameterValue
     *            form parameter value
     */
    static void require(String parameterName, Object parameterValue) {
        if (parameterValue == null) {
            HeraErrorMessage errorMessage = new HeraErrorMessage(parameterName + " is a required parameter");
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build());
        }
    }

    private ParamUtil() {
    }
}
