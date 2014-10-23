package org.theeuropeanlibrary.hera.rest.administration;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.theeuropeanlibrary.maia.common.model.Provider;

/**
 * Jersey Configuration for Exception mappers and Resources
 *
 * @author Emmanouil Koufakis (emmanouil.koufakis@kb.nl)
 * @since 17.10.2014
 */
public class JerseyConfig extends ResourceConfig {
    /**
     * Creates a new instance of this class.
     */
    public JerseyConfig() {
        super();
        register(RequestContextFilter.class);
        register(Provider.class);
        register(LoggingFilter.class);
        register(ProvidersResource.class);
    }
}
