package org.theeuropeanlibrary.hera.rest.administration;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.theeuropeanlibrary.hera.rest.administration.objectmapper.TelObjectMapper;

/**
 * Jersey Configuration for Exception Mappers and Resources
 * 
 */
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		super();
		
        register(ProviderResource.class);
        register(ProvidersResource.class);
        
        register(TelObjectMapper.class);

        register(DatasetResource.class);
        register(DatasetsResource.class);

        register(RequestContextFilter.class);
        register(LoggingFilter.class);
	}
}
