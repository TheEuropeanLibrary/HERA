package org.theeuropeanlibrary.hera.rest.administration.service;

import java.util.List;
import org.theeuropeanlibrary.maia.common.definitions.Provider;

//TODO: needs to be moved to separate project
public interface ProviderService<T> {

    Provider<T> createProvider();

    boolean updateProvider(Provider<T> provider);

    boolean deleteProvider(T providerId);

    List<Provider<T>> getProviders(T startProviderId, int numberOfProviders);
}
