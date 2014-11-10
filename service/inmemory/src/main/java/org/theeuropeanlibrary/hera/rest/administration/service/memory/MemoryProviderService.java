package org.theeuropeanlibrary.hera.rest.administration.service.memory;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.theeuropeanlibrary.hera.rest.administration.service.ProviderService;
import org.theeuropeanlibrary.hera.rest.administration.service.memory.dao.MemoryProviderDao;
import org.theeuropeanlibrary.maia.common.definitions.Provider;

/**
 *
 * @author Markus Muhr (markus.muhr@theeuropeanlibrary.org)
 * @since 10.11.2014
 */
public class MemoryProviderService implements ProviderService {

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
    public Provider createProvider() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateProvider(Provider provider) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteProvider(Object providerId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List getProviders(Object startProviderId, int numberOfProviders) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
