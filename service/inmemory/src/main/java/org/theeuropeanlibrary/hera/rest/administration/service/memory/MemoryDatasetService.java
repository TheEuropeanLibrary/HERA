package org.theeuropeanlibrary.hera.rest.administration.service.memory;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.theeuropeanlibrary.hera.rest.administration.service.DatasetService;
import org.theeuropeanlibrary.hera.rest.administration.service.exception.DatasetDoesNotExistException;
import org.theeuropeanlibrary.hera.rest.administration.service.memory.dao.MemoryDatasetDao;
import org.theeuropeanlibrary.maia.common.definitions.Dataset;

/**
 *
 * @author Markus Muhr (markus.muhr@theeuropeanlibrary.org)
 * @since 10.11.2014
 */
public class MemoryDatasetService implements DatasetService<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemoryDatasetService.class);

    @Autowired
    private MemoryDatasetDao datasetDao;

    /**
     * Creates a new instance of this class.
     */
    public MemoryDatasetService() {
        LOGGER.info("MemoryDatasetService started successfully...");
    }

    /**
     * Creates a new instance of this class and initializes the service with
     * given data access objects.
     *
     * @param datasetDao data access for datasets
     */
    public MemoryDatasetService(MemoryDatasetDao datasetDao) {
        LOGGER.info("MemoryDatasetService starting...");
        this.datasetDao = datasetDao;
        LOGGER.info("MemoryDatasetService started successfully.");
    }

	@Override
	public Dataset<String> createDataSet(String providerId, Dataset<String> dataset) {
		
    	final String datasetId = UUID.randomUUID().toString();
    	dataset.setId(datasetId);
    	datasetDao.createDataset(datasetId, providerId, dataset);
    	return dataset;
	}


	@Override
	public void updateDataSet(String datasetId, Dataset dataSet) throws DatasetDoesNotExistException {
		
		final boolean resultOK = datasetDao.updateDataSet(datasetId, dataSet);
    	if (!resultOK) {
    		final String errorMessage = String.format("DatasetId [%s] not found.", datasetId);
    		throw new DatasetDoesNotExistException(errorMessage);
    	}
	}

	@Override
	public void deleteDataSet(String datasetId) throws DatasetDoesNotExistException {
		final boolean resultOK = datasetDao.deleteDataSet(datasetId);
		if (!resultOK) {
    		final String errorMessage = String.format("DatasetId [%s] not found.", datasetId);
    		throw new DatasetDoesNotExistException(errorMessage);
		}
	}
	
	@Override
	public Dataset<String> getDataset(String datasetId) throws DatasetDoesNotExistException {
		
    	Dataset<String> d = datasetDao.getDataset(datasetId);
		if (d == null) {
    		final String errorMessage = String.format("DatasetId [%s] not found.", datasetId);
			throw new DatasetDoesNotExistException(errorMessage);
		}
		return d;
	}

	@Override
	public Collection<Dataset<String>> getDataSetsForProvider(String providerId, String startDatasetId, int numberOfDatasets) {
		Collection<Dataset<String>> datasets = datasetDao.getDataSetsForProvider(providerId, startDatasetId, numberOfDatasets);
		return datasets;
	}

	@Override
	public List<Dataset<String>> getDataSets(String startDatasetId,	int numberOfDatasets) {
		List<Dataset<String>> datasets = datasetDao.getDatasets(startDatasetId, numberOfDatasets);
		return datasets;
	}
}
