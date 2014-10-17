package org.theeuropeanlibrary.hera.rest.administration;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.theeuropeanlibrary.maia.common.model.DataProvider;

/**
 * Jersey Configuration for Exception Mappers and Resources
 * 
 */
public class JerseyConfig extends ResourceConfig {

	/**
	 * Creates a new instance of this class.
	 */
	public JerseyConfig() {
		super();
        register(RequestContextFilter.class);
        register(DataProvider.class);
        register(LoggingFilter.class);
        register(DataProviderResource.class);
	}
}
