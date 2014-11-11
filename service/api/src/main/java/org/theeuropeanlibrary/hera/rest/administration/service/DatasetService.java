package org.theeuropeanlibrary.hera.rest.administration.service;

import java.util.List;

import org.theeuropeanlibrary.hera.rest.administration.service.exception.DatasetDoesNotExistException;
import org.theeuropeanlibrary.maia.common.definitions.Dataset;

public interface DatasetService<T> {

    Dataset<T> createDataSet(Dataset<T> dataSet);
    
	Dataset<T> getDataset(T datasetId) throws DatasetDoesNotExistException;
    
    void updateDataSet(String datasetId, Dataset<T> dataSet) 
    		throws DatasetDoesNotExistException;
    
    void deleteDataSet(T dataSetId) 
    		throws DatasetDoesNotExistException;

    List<Dataset<T>> getDataSets(T startDatasetId, int numberOfDatasets);
    
    List<Dataset<T>> getDataSetsForProvider(T providerId, T startDatasetId, int numberOfDatasets);
}
