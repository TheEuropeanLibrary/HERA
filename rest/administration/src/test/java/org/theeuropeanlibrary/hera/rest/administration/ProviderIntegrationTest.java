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
import org.theeuropeanlibrary.maia.common.converter.ConverterException;
import org.theeuropeanlibrary.maia.common.definitions.Provider;
import org.theeuropeanlibrary.maia.converter.json.EntityObjectMapper;
import org.theeuropeanlibrary.maia.converter.json.ProviderEntityJsonConverter;
import org.theeuropeanlibrary.maia.tel.model.common.qualifier.Country;
import org.theeuropeanlibrary.maia.tel.model.provider.ProviderKeys;
import org.theeuropeanlibrary.maia.tel.model.provider.ProviderRegistry;

/**
 * Requires a running HERA-administration instance at: http://localhost:8081/hera-rest-administration
 * 
 * Tests CREATE / GET / DELETE / UPDATE -> provider
 */
public class ProviderIntegrationTest {
	
	//TODO 
//	@Autowired
//	private ObjectMapper objectMapper;
	
    private final static String PROVIDER_PHONE = "00316971902669";
    private final static String PROVIDER_NAME = "Markus_Magic_Mushroom_Provider";
    
    private final static String PROVIDER_NAME_UPDATED = "Markus_Magic_Mushroom_Provider_UPDATED_TO_lollipop";
    private final static String PROVIDER_PHONE_UPDATED = "00316971902669_UPDATED_TO_lollipop";
    
	private EntityObjectMapper objectMapper;
	
	private ProviderEntityJsonConverter converter;
	
	private HttpAuthenticationFeature basicAuthentication = HttpAuthenticationFeature.universalBuilder()
		      .credentialsForBasic("Alina", "Alina")
		      .build();

    private Client client = JerseyClientBuilder.newClient().register(basicAuthentication);

    private static final String BASE_URL = "http://localhost:8081/hera-rest-administration";
    
    private static final String createProviderPathTemplate = "/providers" ;
    private static final String getProviderPathTemplate = "/providers" + "/{" + ParamConstants.P_PROVIDER + "}";
    private static final String updateProviderPathTemplate = "/providers" + "/{" + ParamConstants.P_PROVIDER + "}";
    private static final String deleteProviderPathTemplate = "/providers" + "/{" + ParamConstants.P_PROVIDER + "}";
    
    @Before
    public void setup() {

        objectMapper = new EntityObjectMapper(ProviderRegistry.INSTANCE, null, null);
        assertNotNull(objectMapper);
        converter = new ProviderEntityJsonConverter(objectMapper);
    }

    /**
     * Create / Get / Update / Delete
     */
//TODO: this works but requires a running instance
//  @Test
    public void fullProviderIntegrationTest() throws ConverterException {
    	
    	Provider<String> providerToIngest = new Provider();
    	providerToIngest.addValue(ProviderKeys.COUNTRY, Country.GR);
    	providerToIngest.addValue(ProviderKeys.PHONE, PROVIDER_PHONE);
    	providerToIngest.addValue(ProviderKeys.NAME, PROVIDER_NAME);
    	
        String encodedProvider = converter.encode(providerToIngest);
        System.out.println(encodedProvider);
        
        ObjectMapperContextResolver r = new ObjectMapperContextResolver();
        r.setObjectMapper(objectMapper);
        client.register(r);
        
        // CREATE //
    	Response resp = client.target(BASE_URL).path(createProviderPathTemplate)
    			.request()
    			.post(Entity.json(providerToIngest));
    	
        Provider<String> createdProvider = resp.readEntity(Provider.class);
        assertThat(resp.getStatus(), equalTo(200));
        
        // GET //
        
        // try to get back the created provider 
    	Response getResponse = client.target(BASE_URL).path(getProviderPathTemplate)
    			.resolveTemplate(ParamConstants.P_PROVIDER, createdProvider.getId())
    			.request()
    			.get();

        Provider<String> getProvider = getResponse.readEntity(Provider.class);
        assertThat(getResponse.getStatus(), equalTo(200));
        assertThat(getProvider.getId(), equalTo(createdProvider.getId()));
        assertThat(getProvider.getFirstValue(ProviderKeys.PHONE), equalTo(PROVIDER_PHONE));
        assertThat(getProvider.getFirstValue(ProviderKeys.NAME), equalTo(PROVIDER_NAME));

        // make an invalid get request
    	Response getResponse404 = client.target(BASE_URL).path(getProviderPathTemplate)
    			.resolveTemplate(ParamConstants.P_PROVIDER, "XXX")
    			.request()
    			.get();
        assertThat(getResponse404.getStatus(), equalTo(404));
    	
        // UPDATE //

    	Provider<String> providerToUpdate = new Provider();
    	providerToUpdate.setId(createdProvider.getId());
    	providerToUpdate.addValue(ProviderKeys.COUNTRY, Country.GR);
    	providerToUpdate.addValue(ProviderKeys.PHONE, PROVIDER_PHONE_UPDATED);
    	providerToUpdate.addValue(ProviderKeys.NAME, PROVIDER_NAME_UPDATED);
    	
    	Response updateResponse = client.target(BASE_URL).path(updateProviderPathTemplate)
    			.resolveTemplate(ParamConstants.P_PROVIDER, createdProvider.getId())
    			.request()
    			.put(Entity.json(providerToUpdate));
    	
    	// lets make sure the provider has been updated
        // try to get back the updated provider 
    	Response getUpdatedProviderResponse = client.target(BASE_URL).path(getProviderPathTemplate)
    			.resolveTemplate(ParamConstants.P_PROVIDER, createdProvider.getId())
    			.request()
    			.get();

        Provider<String> updatedProvider = getUpdatedProviderResponse.readEntity(Provider.class);
        assertThat(updateResponse.getStatus(), equalTo(200));
        assertThat(updatedProvider.getId(), equalTo(createdProvider.getId()));
        assertThat(updatedProvider.getFirstValue(ProviderKeys.PHONE), equalTo(PROVIDER_PHONE_UPDATED));
        assertThat(updatedProvider.getFirstValue(ProviderKeys.NAME), equalTo(PROVIDER_NAME_UPDATED));

        // DELETE //
        
        // try to delete the created provider
    	Response deleteResponse = client.target(BASE_URL).path(deleteProviderPathTemplate)
    			.resolveTemplate(ParamConstants.P_PROVIDER, createdProvider.getId())
    			.request()
                .delete();
        assertThat(deleteResponse.getStatus(), equalTo(200));
        

        // try to delete the same provider again (should not work, it's already deleted)
    	Response deleteResponse404 = client.target(BASE_URL).path(deleteProviderPathTemplate)
    			.resolveTemplate(ParamConstants.P_PROVIDER, createdProvider.getId())
    			.request()
                .delete();
        assertThat(deleteResponse404.getStatus(), equalTo(404));
    }
}
