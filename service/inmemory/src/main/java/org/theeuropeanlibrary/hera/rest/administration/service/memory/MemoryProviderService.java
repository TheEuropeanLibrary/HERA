package org.theeuropeanlibrary.hera.rest.administration.service.memory;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.theeuropeanlibrary.hera.rest.administration.service.ProviderService;
import org.theeuropeanlibrary.hera.rest.administration.service.exception.DatasetDoesNotExistException;
import org.theeuropeanlibrary.hera.rest.administration.service.exception.ProviderDoesNotExistException;
import org.theeuropeanlibrary.hera.rest.administration.service.memory.dao.MemoryProviderDao;
import org.theeuropeanlibrary.maia.common.definitions.Provider;

/**
 *
 * @author Markus Muhr (markus.muhr@theeuropeanlibrary.org)
 * @since 10.11.2014
 */
public class MemoryProviderService implements ProviderService<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemoryProviderService.class);

    @Autowired
    private MemoryProviderDao providerDao;

    /**
     * Creates a new instance of this class.
     */
    public MemoryProviderService() {
        LOGGER.info("MemoryProviderService started successfully...");
    }

    /**
     * Creates a new instance of this class and initializes the service with
     * given data access objects.
     *
     * @param providerDao data access for providers
     */
    public MemoryProviderService(MemoryProviderDao providerDao) {
        LOGGER.info("MemoryProviderService starting...");
        this.providerDao = providerDao;
        LOGGER.info("MemoryProviderService started successfully.");
    }

    @Override
    public Provider<String> createProvider(Provider<String> provider) {
    	
    	final String id = UUID.randomUUID().toString();
    	provider.setId(id);
    	providerDao.createProvider(id, provider);
		return provider;
    }

    @Override
    public void updateProvider(String providerId, Provider<String> provider) throws ProviderDoesNotExistException {
    	
    	if (!providerId.equals(provider.getId())) {
    		throw new RuntimeException("ProviderId cannot be updated!");
    	}
    	
		final boolean resultOK = providerDao.updateProvider(providerId, provider);
    	if (!resultOK) {
    		final String errorMessage = String.format("Provider [%s] not found.", providerId);
    		throw new ProviderDoesNotExistException(errorMessage);
    	}
    }

	@Override
	public void deleteProvider(String providerId) throws ProviderDoesNotExistException {
		
		final boolean resultOK = providerDao.deleteProvider(providerId);
    	if (!resultOK) {
    		final String errorMessage = String.format("Provider [%s] not found.", providerId);
    		throw new ProviderDoesNotExistException(errorMessage);
    	}
	}

	@Override
	public List<Provider<String>> getProviders(String startProviderId, int numberOfProviders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Provider<String> getProvider(String providerId) throws ProviderDoesNotExistException {
		
		Provider<String> p = providerDao.getProvider(providerId);
		if (p == null) {
    		final String errorMessage = String.format("Provider [%s] not found.", providerId);
			throw new ProviderDoesNotExistException(errorMessage);
		}
		return p;
	}
}
