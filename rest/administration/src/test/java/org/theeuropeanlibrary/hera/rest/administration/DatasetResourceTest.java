package org.theeuropeanlibrary.hera.rest.administration;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.theeuropeanlibrary.hera.rest.administration.service.DatasetService;
import org.theeuropeanlibrary.hera.rest.administration.utils.ApplicationContextUtils;

/**
 * TODO: add more tests
 */
public class DatasetResourceTest extends JerseyTest {

    private String datasetId = "datasetId";

    private DatasetService datasetService;

    @Before
    public void mockUp() {

        ApplicationContext applicationContext = ApplicationContextUtils
                .getApplicationContext();
        assertNotNull(applicationContext);

        datasetService = applicationContext.getBean(DatasetService.class);

        assertNotNull(datasetService);
        Mockito.reset(datasetService);
    }

    @Override
    public Application configure() {
        return new ResourceConfig()
                .registerClasses(DatasetResource.class)
                .registerClasses(ProviderResource.class)
                .registerClasses(RequestContextFilter.class)
                .property("contextConfigLocation",
                        "classpath:hera-administration-context-test.xml");
    }

    @Test
    public void deleteDataset() throws Exception {
        assertNotNull(datasetService);

        Response response = target().path("/datasets/" + datasetId).request().delete();
//        assertThat(response.getStatus(), equalTo(204));

        verify(datasetService, times(1)).deleteDataSet(datasetId);
        verifyNoMoreInteractions(datasetService);
    }
}
