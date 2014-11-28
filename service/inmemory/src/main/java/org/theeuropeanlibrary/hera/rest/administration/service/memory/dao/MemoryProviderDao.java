package org.theeuropeanlibrary.hera.rest.administration.service.memory.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.theeuropeanlibrary.maia.common.definitions.Provider;
import org.theeuropeanlibrary.maia.common.filter.EntityFilter;
import org.theeuropeanlibrary.maia.tel.model.provider.ProviderRegistry;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Dummy in-memory implementation.
 * Not really accurate but good for now.
 * 
 * @author Markus Muhr (markus.muhr@theeuropeanlibrary.org)
 * @since 10.11.2014
 */
public class MemoryProviderDao {
	
	EntityFilter<String, Provider<String>> providersFilter = ProviderRegistry.getInstance().getFilterFactory().getFilterForName("simple");

	private Map<String, Provider<String>> providers = Maps.newHashMap();
	
	public void createProvider(String providerId, Provider<String> provider) {
		providers.put(providerId, provider);
	}

	public boolean updateProvider(String providerId, Provider<String> provider) {
		
		Provider<String> oldData = providers.put(providerId, provider);
		return oldData != null;
	}

	public boolean deleteProvider(String providerId) {
		Provider<String> oldData = providers.remove(providerId);
		return oldData != null;
	}

	public Provider<String> getProvider(String providerId) {
		return providers.get(providerId);
	}

	public List<Provider<String>> getProviders(String startProviderId, int numberOfProviders) {

		List<Provider<String>> providersToReturn = Lists.newArrayList();
		
		Iterator<Provider<String>> i = providers.values().iterator();
		int count = 0;
		while(count < numberOfProviders && i.hasNext()) {
			
			Provider<String> currentProvider = i.next();
			currentProvider = providersFilter.filter(currentProvider);
			providersToReturn.add(currentProvider);
			count++;
		}
		return providersToReturn;
	}	
}
