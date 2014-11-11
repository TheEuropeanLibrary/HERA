package org.theeuropeanlibrary.hera.rest.administration.service.memory.dao;

import java.util.Map;

import org.theeuropeanlibrary.maia.common.definitions.Dataset;

import com.google.common.collect.Maps;

/**
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
}
