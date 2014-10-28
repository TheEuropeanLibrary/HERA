package org.theeuropeanlibrary.hera.rest.administration.service;

import org.theeuropeanlibrary.hera.rest.administration.ResultSlice;
import org.theeuropeanlibrary.maia.common.definitions.Dataset;
import org.theeuropeanlibrary.maia.common.definitions.Provider;


// TODO: needs to be moved to separate project
public interface DatasetService {

	void deleteDataSet(String dataSetId);

	ResultSlice<Dataset> getDataSets(String startFrom, int numberOfDatasets);

	Dataset createDataSet(String dataSetId, String description);

	void updateDataSet(String dataSetId, String description);
}
