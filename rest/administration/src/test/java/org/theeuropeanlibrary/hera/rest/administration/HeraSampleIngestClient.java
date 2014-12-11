package org.theeuropeanlibrary.hera.rest.administration;

import static org.junit.Assert.assertNotNull;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.theeuropeanlibrary.hera.rest.administration.utils.HardcodedExamplesUtil;
import org.theeuropeanlibrary.maia.common.converter.ConverterException;
import org.theeuropeanlibrary.maia.common.definitions.Dataset;
import org.theeuropeanlibrary.maia.common.definitions.Provider;
import org.theeuropeanlibrary.maia.converter.json.EntityObjectMapper;
import org.theeuropeanlibrary.maia.tel.model.dataset.DatasetRegistry;
import org.theeuropeanlibrary.maia.tel.model.provider.ProviderRegistry;


/**
 * Can be used to insert sample data to Hera
 * 
 * (providers, datasets)
 */
public class HeraSampleIngestClient {

	private static EntityObjectMapper objectMapper;

	private static HttpAuthenticationFeature basicAuthentication = HttpAuthenticationFeature
			.universalBuilder().credentialsForBasic("Alina", "Alina").build();
	
	private static Client client = JerseyClientBuilder.newClient().register(
			basicAuthentication);
	
	/** Url to connect to */
	private static final String BASE_URL_LOCALHOST = "http://localhost:8082/hera-rest-administration";
	private static final String BASE_URL_ISTI = "http://146.48.82.158:8080/hera-rest-administration/";
	private static final String BASE_URL = BASE_URL_LOCALHOST;

	private static final String createProviderPathTemplate = "/providers";
	private static final String getProviderPathTemplate = "/providers" + "/{"
			+ ParamConstants.P_PROVIDER + "}";
	private static final String updateProviderPathTemplate = "/providers"
			+ "/{" + ParamConstants.P_PROVIDER + "}";
	private static final String deleteProviderPathTemplate = "/providers"
			+ "/{" + ParamConstants.P_PROVIDER + "}";

	private static final String createDatasetPathTemplate = "/datasets";
	private static final String getDatasetForProviderPathTemplate = "/datasets";
	private static final String getMultipleDatasetsPathTemplate = "/datasets";
	private static final String getDatasetPathTemplate = "/datasets" + "/{"
			+ ParamConstants.P_DATASET + "}";
	private static final String updateDatasetPathTemplate = "/datasets" + "/{"
			+ ParamConstants.P_DATASET + "}";
	private static final String deleteDatasetPathTemplate = "/datasets" + "/{"
			+ ParamConstants.P_DATASET + "}";

	public static void main(String[] args) throws ConverterException {

		objectMapper = new EntityObjectMapper(ProviderRegistry.getInstance(),
				DatasetRegistry.getInstance(), null);
		assertNotNull(objectMapper);

		ingest();
	}

	private static void ingest() throws ConverterException {

		Provider<String> providerToIngest = HardcodedExamplesUtil
				.createProviders();

		ObjectMapperContextResolver r = new ObjectMapperContextResolver();
		r.setObjectMapper(objectMapper);
		client.register(r);

		// CREATE PROVIDER //
		Response resp = client.target(BASE_URL)
				.path(createProviderPathTemplate).request()
				.post(Entity.json(providerToIngest));

		Provider<String> createdProvider = resp.readEntity(Provider.class);

		// GET PROVIDER//

		// try to get back the created provider
		Response getResponse = client
				.target(BASE_URL)
				.path(getProviderPathTemplate)
				.resolveTemplate(ParamConstants.P_PROVIDER,
						createdProvider.getId()).request().get();

		Provider<String> getProvider = getResponse.readEntity(Provider.class);

		// CREATE DATASET //
		Dataset<String> datasetToIngest = HardcodedExamplesUtil
				.createDatasets();
		
		Response resp1 = client.target(BASE_URL)
				.path(createDatasetPathTemplate)
				.queryParam(ParamConstants.Q_PROVIDER, getProvider.getId())
				.request().post(Entity.json(datasetToIngest));

		Dataset<String> createdDataset = resp1.readEntity(Dataset.class);
	}
}
