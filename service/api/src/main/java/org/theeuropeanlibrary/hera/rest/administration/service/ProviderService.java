package org.theeuropeanlibrary.hera.rest.administration.service;

import java.util.List;
import org.theeuropeanlibrary.maia.common.definitions.Provider;

//TODO: needs to be moved to separate project
public interface ProviderService {

    Provider createProvider(String providerId, String dataProviderProperties);

    Provider updateProvider(String providerId, String dataProviderProperties);

    void deleteProvider(String providerId);

    List<Provider> getProviders(String startFrom, int numberOfProviders);
}
