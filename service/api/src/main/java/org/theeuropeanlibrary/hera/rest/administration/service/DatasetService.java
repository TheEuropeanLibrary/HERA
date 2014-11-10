package org.theeuropeanlibrary.hera.rest.administration.service;

import java.util.List;
import org.theeuropeanlibrary.maia.common.definitions.Dataset;
import org.theeuropeanlibrary.maia.common.definitions.Provider;

// TODO: needs to be moved to separate project
public interface DatasetService<T> {

    Dataset<T> createDataSet(Provider<T> provider);
    
    boolean updateDataSet(Dataset<T> dataSet);
    
    void deleteDataSet(T dataSetId);
    
    List<Dataset<T>> getDataSetsForProvider(T providerId, T startDatasetId, int numberOfDatasets);

    List<Dataset<T>> getDataSets(T startDatasetId, int numberOfDatasets);
}
