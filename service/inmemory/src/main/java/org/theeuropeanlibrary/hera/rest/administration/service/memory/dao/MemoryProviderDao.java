package org.theeuropeanlibrary.hera.rest.administration.service.memory.dao;

import java.util.Map;

import org.theeuropeanlibrary.maia.common.definitions.Provider;

import com.google.common.collect.Maps;

/**
 *
 * @author Markus Muhr (markus.muhr@theeuropeanlibrary.org)
 * @since 10.11.2014
 */
public class MemoryProviderDao {

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
}
