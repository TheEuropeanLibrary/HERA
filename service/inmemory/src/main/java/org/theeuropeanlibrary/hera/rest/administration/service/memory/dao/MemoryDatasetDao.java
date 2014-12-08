package org.theeuropeanlibrary.hera.rest.administration.service.memory.dao;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.theeuropeanlibrary.maia.common.definitions.Dataset;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

/**
 * Dummy in-memory implementation.
 * Not really accurate but good for now.
 *
 * @author Markus Muhr (markus.muhr@theeuropeanlibrary.org)
 * @since 10.11.2014
 */
public class MemoryDatasetDao {
	
	private Map<String, Dataset<String>> datasetsByDatasetId = Maps.newHashMap();
	private Multimap<String, Dataset<String>> datasetsByProviderId = ArrayListMultimap.create();

	public Dataset<String> createDataset(String datasetId, String providerId, Dataset<String> dataset) {

		datasetsByDatasetId.put(datasetId, dataset);
		datasetsByProviderId.put(providerId, dataset);
		return dataset;
	}

	public boolean updateDataSet(String datasetId, Dataset<String> dataset) {

		datasetsByDatasetId.put(datasetId, dataset);

		final String providerId = findProviderIdForDataset(datasetId);
		if (providerId != null) {
			datasetsByProviderId.put(providerId, dataset);
		}
		
		return true;
	}

	public boolean deleteDataSet(String datasetId) {
		
		Dataset<String> oldData = datasetsByDatasetId.remove(datasetId);
		
		final String providerId = findProviderIdForDataset(datasetId);
		if (providerId != null) {
			datasetsByProviderId.remove(providerId, oldData);
		}
		return oldData != null;
	}

	public Dataset<String> getDataset(String datasetId) {
		return datasetsByDatasetId.get(datasetId);
	}

	public List<Dataset<String>> getDatasets(String startDatasetId, int numberOfDatasets) {
		
		List<Dataset<String>> datasetsToReturn = Lists.newArrayList();
		
		Iterator<Dataset<String>> i = datasetsByDatasetId.values().iterator();
		int count = 0;
		while(count < numberOfDatasets && i.hasNext()) {
			datasetsToReturn.add(i.next());
			count++;
		}
		return datasetsToReturn;
	}

	/**
	 * @return returns subset of total datasets
	 */
	public Collection<Dataset<String>> getDataSetsForProvider(String providerId, String startDatasetId, int numberOfDatasets) {
		return datasetsByProviderId.get(providerId);
	}
	
	/**
	 * @return ProviderId where the specified dataset belongs to
	 * 
	 * Only the first provider key found is returned.
	 */
	private String findProviderIdForDataset(String datasetId) {
		
		Set<String> pKeys = datasetsByProviderId.keySet();
		for (final String providerKey: pKeys) {
			
			Collection<Dataset<String>> datasetsForCurrentProvider = datasetsByProviderId.get(providerKey);
			for (final Dataset d: datasetsForCurrentProvider) {
				if (d.getId().equals(datasetId)) {
					return providerKey;
				}
			}
		}
		return null;
	}
}
