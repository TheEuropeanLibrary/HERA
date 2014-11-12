package org.theeuropeanlibrary.hera.rest.administration.service;

import java.util.List;

import org.theeuropeanlibrary.hera.rest.administration.service.exception.ProviderDoesNotExistException;
import org.theeuropeanlibrary.maia.common.definitions.Provider;

public interface ProviderService<T> {

    Provider<T> createProvider(Provider<T> provider);

    void updateProvider(T providerId, Provider<T> provider) throws ProviderDoesNotExistException;

    void deleteProvider(T providerId) throws ProviderDoesNotExistException;

    List<Provider<T>> getProviders(T startProviderId, int numberOfProviders) throws ProviderDoesNotExistException;

	Provider<String> getProvider(String providerId) throws ProviderDoesNotExistException;
}
