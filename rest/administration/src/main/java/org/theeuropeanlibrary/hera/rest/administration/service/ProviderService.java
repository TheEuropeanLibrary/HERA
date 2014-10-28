package org.theeuropeanlibrary.hera.rest.administration.service;

import org.theeuropeanlibrary.hera.rest.administration.ResultSlice;
import org.theeuropeanlibrary.maia.common.definitions.Provider;


//TODO: needs to be moved to separate project
public interface ProviderService {

	public Provider createProvider(String providerId, String dataProviderProperties);

	public Provider updateProvider(String providerId, String dataProviderProperties);

	public void deleteProvider(String providerId);

	public ResultSlice<Provider> getProviders(String startFrom, int numberOfProviders);
}
