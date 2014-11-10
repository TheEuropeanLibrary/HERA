package org.theeuropeanlibrary.hera.rest.administration.service;

import java.util.List;
import org.theeuropeanlibrary.maia.common.definitions.Dataset;

// TODO: needs to be moved to separate project
public interface DatasetService {

    void deleteDataSet(String dataSetId);

    List<Dataset> getDataSets(String startFrom, int numberOfDatasets);

    Dataset createDataSet(String dataSetId, String description);

    void updateDataSet(String dataSetId, String description);
}
