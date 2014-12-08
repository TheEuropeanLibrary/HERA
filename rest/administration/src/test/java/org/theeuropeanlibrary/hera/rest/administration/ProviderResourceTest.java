package org.theeuropeanlibrary.hera.rest.administration;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.theeuropeanlibrary.hera.rest.administration.service.ProviderService;
import org.theeuropeanlibrary.hera.rest.administration.utils.ApplicationContextUtils;

/**
 * TODO: add more tests
 */
public class ProviderResourceTest extends JerseyTest {

    private String providerId = "providerId";

    private ProviderService providerService;

    @Before
    public void mockUp() {

        ApplicationContext applicationContext = ApplicationContextUtils
                .getApplicationContext();
        assertNotNull(applicationContext);

        providerService = applicationContext.getBean(ProviderService.class);

        assertNotNull(providerService);
        Mockito.reset(providerService);
    }

    @Override
    public Application configure() {
        return new ResourceConfig()
                .registerClasses(DatasetResource.class)
                .registerClasses(ProviderResource.class)
                .registerClasses(RequestContextFilter.class)
                .property("contextConfigLocation",
                        "classpath:hera-administration-context-test-mock.xml");
    }

//    @Test
    public void deleteProvider() throws Exception {
        assertNotNull(providerService);

        Response response = target().path("/providers/" + providerId).request().delete();
//        assertThat(response.getStatus(), equalTo(204));

        verify(providerService, times(1)).deleteProvider(providerId);
        verifyNoMoreInteractions(providerService);
    }
}
