package org.theeuropeanlibrary.hera.rest.administration.service;

import org.theeuropeanlibrary.maia.common.definitions.Provider;

//TODO: needs to be moved to separate project
public interface ProviderService {

    Provider createProvider(String providerId, String dataProviderProperties);

    Provider updateProvider(String providerId, String dataProviderProperties);

    void deleteProvider(String providerId);

    ResultSlice<Provider> getProviders(String startFrom, int numberOfProviders);
}
