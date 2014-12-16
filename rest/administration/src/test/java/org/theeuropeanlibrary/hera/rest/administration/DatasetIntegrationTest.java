package org.theeuropeanlibrary.hera.rest.administration;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.Before;
import org.junit.Test;
import org.theeuropeanlibrary.hera.rest.administration.utils.HardcodedExamplesUtil;
import org.theeuropeanlibrary.maia.common.Entity.QualifiedValue;
import org.theeuropeanlibrary.maia.common.converter.ConverterException;
import org.theeuropeanlibrary.maia.common.definitions.Dataset;
import org.theeuropeanlibrary.maia.converter.json.DatasetEntityJsonConverter;
import org.theeuropeanlibrary.maia.converter.json.EntityObjectMapper;
import org.theeuropeanlibrary.maia.tel.model.common.qualifier.Language;
import org.theeuropeanlibrary.maia.tel.model.dataset.DatasetKeys;
import org.theeuropeanlibrary.maia.tel.model.dataset.DatasetRegistry;
import org.theeuropeanlibrary.maia.tel.model.dataset.definitions.LinkType;
import org.theeuropeanlibrary.maia.tel.model.dataset.definitions.descriptions.CollectionDescription;
import org.theeuropeanlibrary.maia.tel.model.provider.ProviderRegistry;

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

    private final static String PROVIDER_ID = "PROVIDER_ID";

    private final static String COL_TITLE = "COLLECTION_TITLE";
    private final static String COL_DESCRIPTION = "COLLECTION_DESCRIPTION";
    
	private EntityObjectMapper objectMapper;
	
	private DatasetEntityJsonConverter converter;
	
	private HttpAuthenticationFeature basicAuthentication = HttpAuthenticationFeature.universalBuilder()
		      .credentialsForBasic("Alina", "Alina")
		      .build();

    private Client client = JerseyClientBuilder.newClient().register(basicAuthentication);

//    private static final String BASE_URL = "http://localhost:8082/hera-rest-administration";
	 private static final String BASE_URL =  "http://146.48.82.158:8080/hera-rest-administration/";
    
    private static final String createDatasetPathTemplate = "/datasets";
    private static final String getDatasetForProviderPathTemplate = "/datasets" ;
    private static final String getMultipleDatasetsPathTemplate = "/datasets" ;
    private static final String getDatasetPathTemplate = "/datasets" + "/{" + ParamConstants.P_DATASET + "}";
    private static final String updateDatasetPathTemplate = "/datasets" + "/{" + ParamConstants.P_DATASET + "}";
    private static final String deleteDatasetPathTemplate = "/datasets" + "/{" + ParamConstants.P_DATASET + "}";
    
    @Before
    public void setup() {

		objectMapper = new EntityObjectMapper(ProviderRegistry.getInstance(),
				DatasetRegistry.getInstance(), null);
		
        assertNotNull(objectMapper);
        converter = new DatasetEntityJsonConverter(objectMapper);
    }

    /**
     * Create / Get / Update / Delete
     */
//TODO: this works but requires a running instance
    @Test
    public void fullDatasetIntegrationTest() throws ConverterException {
    	
    	// first dataset is a super simple example
    	Dataset<String> datasetToIngest = new Dataset<String>();
    	datasetToIngest.addValue(DatasetKeys.NAME, DATASET_NAME);
    	
    	// second dataset is a full example
    	Dataset<String> datasetToIngest2 = HardcodedExamplesUtil.createDatasets();
    	
        ObjectMapperContextResolver r = new ObjectMapperContextResolver();
        r.setObjectMapper(objectMapper);
        client.register(r);
        
        // CREATE //
        
    	// create one dataset
    	Response resp1 = client.target(BASE_URL).path(createDatasetPathTemplate)
    			.queryParam(ParamConstants.Q_PROVIDER, PROVIDER_ID)
    			.request()
    			.post(Entity.json(datasetToIngest));
    	
        assertThat(resp1.getStatus(), equalTo(200));
    	Dataset<String> createdDataset = resp1.readEntity(Dataset.class);
    	
    	// create one more dataset
    	Response resp2 = client.target(BASE_URL).path(createDatasetPathTemplate)
    			.queryParam(ParamConstants.Q_PROVIDER, PROVIDER_ID)
    			.request()
    			.post(Entity.json(datasetToIngest2));
        assertThat(resp2.getStatus(), equalTo(200));
    	Dataset<String> createdDataset2 = resp2.readEntity(Dataset.class);
        
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
    			.resolveTemplate(ParamConstants.P_DATASET, PROVIDER_ID)
    			.request()
    			.get();
        assertThat(getResponse404.getStatus(), equalTo(404));
        
        // make a get request for datasets of specific provider
    	Response getResponseForSpecificProvider = client.target(BASE_URL).path(getDatasetForProviderPathTemplate)
    			.queryParam(ParamConstants.Q_PROVIDER, PROVIDER_ID)
    			.request()
    			.get();
        assertThat(getResponseForSpecificProvider.getStatus(), equalTo(200));
        ResultSlice<Dataset<String>> resultSlice = getResponseForSpecificProvider.readEntity(ResultSlice.class);
        assertThat(resultSlice.getResults().size(), greaterThan(1)); // there was 2 datasets inserted with this providerId
        
        // make a get request for multiple datasets
    	Response getResponseForMultipleDatasets = client.target(BASE_URL).path(getMultipleDatasetsPathTemplate)
    			.request()
    			.get();
        assertThat(getResponseForMultipleDatasets.getStatus(), equalTo(200));
        ResultSlice<Dataset<String>> resultSliceForMultipleDatasets = getResponseForMultipleDatasets.readEntity(ResultSlice.class);
        assertThat(resultSliceForMultipleDatasets.getResults().size(), greaterThan(1));
        
        // UPDATE //

        // update using filter "description"
        Dataset<String> datasetToUpdate = new Dataset();
        datasetToUpdate.setId(createdDataset2.getId());
        CollectionDescription cDoriginal = new CollectionDescription(COL_TITLE, COL_DESCRIPTION);
        datasetToUpdate.addValue(DatasetKeys.COLLECTION_DESCRIPTION, cDoriginal, Language.AAR);
        
        String encodedDataset = converter.encode(datasetToUpdate);
        System.out.println(encodedDataset);
        
    	Response updateResponse = client.target(BASE_URL).path(updateDatasetPathTemplate)
    			.resolveTemplate(ParamConstants.P_DATASET, createdDataset2.getId())
    			.queryParam(ParamConstants.Q_FILTER, "description")
    			.request()
    			.put(Entity.json(datasetToUpdate));
    	
        assertThat(updateResponse.getStatus(), equalTo(200));
    	
    	// lets make sure the dataset has been updated
        // try to get back the updated dataset 
    	Response getUpdatedDatasetResponse = client.target(BASE_URL).path(getDatasetPathTemplate)
    			.resolveTemplate(ParamConstants.P_DATASET, createdDataset2.getId())
    			.request()
    			.get();

    	Dataset<String> updatedDataset = getUpdatedDatasetResponse.readEntity(Dataset.class);
        assertThat(updatedDataset.getId(), equalTo(createdDataset2.getId()));
        
        CollectionDescription cDupdated = updatedDataset.getFirstValue(DatasetKeys.COLLECTION_DESCRIPTION);
        assertThat(cDupdated, equalTo(cDoriginal));

        // make sure old values still remain the same
        assertThat(datasetToIngest2.getFirstValue(DatasetKeys.COUNTRY), equalTo(updatedDataset.getFirstValue(DatasetKeys.COUNTRY)));
        
        // update using filter "portal"
        Dataset<String> datasetToUpdate2 = new Dataset();
        datasetToUpdate2.setId(createdDataset2.getId());
        String oriniginalLink = "www.itsfriday.com";
        datasetToUpdate2.addValue(DatasetKeys.LINK, oriniginalLink, LinkType.ACCESS);
        
    	Response updateResponse2 = client.target(BASE_URL).path(updateDatasetPathTemplate)
    			.resolveTemplate(ParamConstants.P_DATASET, createdDataset2.getId())
    			.queryParam(ParamConstants.Q_FILTER, "portal")
    			.request()
    			.put(Entity.json(datasetToUpdate2));
    	
        assertThat(updateResponse2.getStatus(), equalTo(200));
    	
    	// lets make sure the dataset has been updated
        // try to get back the updated dataset
    	Response getUpdatedDatasetResponse2 = client.target(BASE_URL).path(getDatasetPathTemplate)
    			.resolveTemplate(ParamConstants.P_DATASET, createdDataset2.getId())
    			.request()
    			.get();

    	Dataset<String> updatedDataset2 = getUpdatedDatasetResponse2.readEntity(Dataset.class);
        assertThat(updatedDataset.getId(), equalTo(createdDataset2.getId()));
        
        String linkUpdated = updatedDataset2.getFirstValue(DatasetKeys.LINK);
        QualifiedValue<String> firstQualifiedValue = updatedDataset2.getFirstQualifiedValue(DatasetKeys.LINK);
        assertThat(firstQualifiedValue.getQualifier(LinkType.class), equalTo(LinkType.ACCESS));
        assertThat(linkUpdated, equalTo(oriniginalLink));

        // make sure old values still remain the same
        assertThat(datasetToIngest2.getFirstValue(DatasetKeys.COUNTRY), equalTo(updatedDataset.getFirstValue(DatasetKeys.COUNTRY)));

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
