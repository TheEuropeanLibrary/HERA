package org.theeuropeanlibrary.hera.rest.administration;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.theeuropeanlibrary.hera.rest.administration.exception.mapper.DatasetAlreadyExistsExceptionMapper;
import org.theeuropeanlibrary.hera.rest.administration.exception.mapper.DatasetDoesNotExistExceptionMapper;
import org.theeuropeanlibrary.hera.rest.administration.exception.mapper.ProviderAlreadyExistsExceptionMapper;
import org.theeuropeanlibrary.hera.rest.administration.exception.mapper.ProviderDoesNotExistExceptionMapper;
import org.theeuropeanlibrary.maia.converter.json.EntityObjectMapper;

/**
 * Jersey Configuration for Exception Mappers and Resources
 *
 */
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        super();

        // JSON serialization
        register(EntityObjectMapper.class);
        
        //features
        register(MultiPartFeature.class);

        // filters
        register(RequestContextFilter.class);
        register(LoggingFilter.class);

        // resources
        register(ProviderResource.class);
        register(ProvidersResource.class);

        register(DatasetResource.class);
        register(DatasetsResource.class);

        // exception mappers
        register(DatasetDoesNotExistExceptionMapper.class);
        register(DatasetAlreadyExistsExceptionMapper.class);
        register(ProviderAlreadyExistsExceptionMapper.class);
        register(ProviderDoesNotExistExceptionMapper.class);
    }
}
