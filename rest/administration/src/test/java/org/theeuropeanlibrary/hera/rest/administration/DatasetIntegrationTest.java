package org.theeuropeanlibrary.hera.rest.administration;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.theeuropeanlibrary.maia.common.converter.ConverterException;
import org.theeuropeanlibrary.maia.common.definitions.Dataset;
import org.theeuropeanlibrary.maia.converter.json.DatasetEntityJsonConverter;
import org.theeuropeanlibrary.maia.converter.json.EntityObjectMapper;
import org.theeuropeanlibrary.maia.tel.model.dataset.DatasetKeys;
import org.theeuropeanlibrary.maia.tel.model.dataset.DatasetRegistry;

/**
 * Requires a running HERA-administration instance at: http://localhost:8082/hera-rest-administration
 * 
 * Tests CREATE / GET / DELETE / UPDATE -> dataset
 */
public class DatasetIntegrationTest {
	
	//TODO: autowire this instead of manually creating it (setup())
//	@Autowired
//	private ObjectMapper objectMapper;
	
    private final static String DATASET_NAME = "DATASET_SIMON";
    private final static String DATASET_NAME_UPDATED = "DATASET_SIMON_UPDATED_TO_lollipop";
    
	private EntityObjectMapper objectMapper;
	
	private DatasetEntityJsonConverter converter;
	
	private HttpAuthenticationFeature basicAuthentication = HttpAuthenticationFeature.universalBuilder()
		      .credentialsForBasic("Alina", "Alina")
		      .build();

    private Client client = JerseyClientBuilder.newClient().register(basicAuthentication);

    private static final String BASE_URL = "http://localhost:8082/hera-rest-administration";
    
    private static final String createDatasetPathTemplate = "/datasets" ;
    private static final String getDatasetPathTemplate = "/datasets" + "/{" + ParamConstants.P_DATASET + "}";
    private static final String updateDatasetPathTemplate = "/datasets" + "/{" + ParamConstants.P_DATASET + "}";
    private static final String deleteDatasetPathTemplate = "/datasets" + "/{" + ParamConstants.P_DATASET + "}";
    
    @Before
    public void setup() {

        objectMapper = new EntityObjectMapper(null, DatasetRegistry.INSTANCE, null);
        assertNotNull(objectMapper);
        converter = new DatasetEntityJsonConverter(objectMapper);
    }

    /**
     * Create / Get / Update / Delete
     */
//TODO: this works but requires a running instance
//    @Test
    public void fullDatasetIntegrationTest() throws ConverterException {
    	
    	Dataset<String> datasetToIngest = new Dataset<String>();
    	datasetToIngest.addValue(DatasetKeys.NAME, DATASET_NAME);
    	
        String encodedDataset = converter.encode(datasetToIngest);
        System.out.println(encodedDataset);
        
        ObjectMapperContextResolver r = new ObjectMapperContextResolver();
        r.setObjectMapper(objectMapper);
        client.register(r);
        
        // CREATE //
    	Response resp = client.target(BASE_URL).path(createDatasetPathTemplate)
    			.request()
    			.post(Entity.json(datasetToIngest));
    	
    	System.out.println(resp);

        assertThat(resp.getStatus(), equalTo(200));
    	Dataset<String> createdDataset = resp.readEntity(Dataset.class);
        
        // GET //
        
        // try to get back the created dataset 
    	Response getResponse = client.target(BASE_URL).path(getDatasetPathTemplate)
    			.resolveTemplate(ParamConstants.P_DATASET, createdDataset.getId())
    			.request()
    			.get();

    	Dataset<String> getDataset = getResponse.readEntity(Dataset.class);
        assertThat(getResponse.getStatus(), equalTo(200));
        assertThat(getDataset.getId(), equalTo(createdDataset.getId()));
        assertThat(getDataset.getFirstValue(DatasetKeys.NAME), equalTo(DATASET_NAME));

        // make an invalid get request
    	Response getResponse404 = client.target(BASE_URL).path(getDatasetPathTemplate)
    			.resolveTemplate(ParamConstants.P_DATASET, "XXX")
    			.request()
    			.get();
        assertThat(getResponse404.getStatus(), equalTo(404));
    	
        // UPDATE //

        Dataset<String> datasetToUpdate = new Dataset();
        datasetToUpdate.setId(createdDataset.getId());
        datasetToUpdate.addValue(DatasetKeys.NAME, DATASET_NAME_UPDATED);
    	
    	Response updateResponse = client.target(BASE_URL).path(updateDatasetPathTemplate)
    			.resolveTemplate(ParamConstants.P_DATASET, createdDataset.getId())
    			.request()
    			.put(Entity.json(datasetToUpdate));
    	
    	// lets make sure the dataset has been updated
        // try to get back the updated dataset 
    	Response getUpdatedDatasetResponse = client.target(BASE_URL).path(getDatasetPathTemplate)
    			.resolveTemplate(ParamConstants.P_DATASET, createdDataset.getId())
    			.request()
    			.get();

    	Dataset<String> updatedDataset = getUpdatedDatasetResponse.readEntity(Dataset.class);
        assertThat(updateResponse.getStatus(), equalTo(200));
        assertThat(updatedDataset.getId(), equalTo(createdDataset.getId()));
        assertThat(updatedDataset.getFirstValue(DatasetKeys.NAME), equalTo(DATASET_NAME_UPDATED));

        // DELETE //
        
        // try to delete the created dataset
    	Response deleteResponse = client.target(BASE_URL).path(deleteDatasetPathTemplate)
    			.resolveTemplate(ParamConstants.P_DATASET, createdDataset.getId())
    			.request()
                .delete();
        assertThat(deleteResponse.getStatus(), equalTo(200));
        

        // try to delete the same dataset again (should not work, it's already deleted)
    	Response deleteResponse404 = client.target(BASE_URL).path(deleteDatasetPathTemplate)
    			.resolveTemplate(ParamConstants.P_DATASET, createdDataset.getId())
    			.request()
                .delete();
        assertThat(deleteResponse404.getStatus(), equalTo(404));
    }
}
