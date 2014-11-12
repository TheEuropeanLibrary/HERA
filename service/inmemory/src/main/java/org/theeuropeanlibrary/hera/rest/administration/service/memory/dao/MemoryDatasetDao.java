package org.theeuropeanlibrary.hera.rest.administration.service.memory.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.theeuropeanlibrary.maia.common.definitions.Dataset;
import org.theeuropeanlibrary.maia.common.definitions.Provider;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Dummy in-memory implementation.
 * Not really accurate but good for now.
 *
 * @author Markus Muhr (markus.muhr@theeuropeanlibrary.org)
 * @since 10.11.2014
 */
public class MemoryDatasetDao {
	
	private Map<String, Dataset<String>> datasets = Maps.newHashMap();

	public Dataset<String> createDataset(String datasetId, Dataset<String> dataset) {
		return datasets.put(datasetId, dataset);
	}

	public boolean updateDataSet(String datasetId, Dataset<String> dataset) {
		
		Dataset<String> oldData = datasets.put(datasetId, dataset);
		return oldData != null;
	}

	public boolean deleteDataSet(String datasetId) {
		Dataset<String> oldData = datasets.remove(datasetId);
		return oldData != null;
	}

	public Dataset<String> getDataset(String datasetId) {
		return datasets.get(datasetId);
	}

	public List<Dataset<String>> getDatasets(String startDatasetId, int numberOfDatasets) {
		
		List<Dataset<String>> datasetsToReturn = Lists.newArrayList();
		
		Iterator<Dataset<String>> i = datasets.values().iterator();
		int count = 0;
		while(count < numberOfDatasets && i.hasNext()) {
			datasetsToReturn.add(i.next());
			count++;
		}
		return datasetsToReturn;
	}

	/**
	 * TODO
	 * Super dummy implementation
	 * @return returns subset of total datasets
	 */
	public List<Dataset<String>> getDataSetsForProvider(String providerId, String startDatasetId, int numberOfDatasets) {

		List<Dataset<String>> datasetsToReturn = Lists.newArrayList();
		
		Iterator<Dataset<String>> i = datasets.values().iterator();
		final int sizeOfStuffToReturn = numberOfDatasets / 2;
		int count = 0;
		
		while(count < sizeOfStuffToReturn && i.hasNext()) {
			
			Dataset<String> d = i.next();
			Provider<String> p = d.getProvider();
			String currentProvidersId = null;
			if (p != null) {
				currentProvidersId = p.getId();
			}
			if(currentProvidersId != null && currentProvidersId.equals(providerId)) {
				datasetsToReturn.add(d);
			}
		}
		return datasetsToReturn;
	}
}
