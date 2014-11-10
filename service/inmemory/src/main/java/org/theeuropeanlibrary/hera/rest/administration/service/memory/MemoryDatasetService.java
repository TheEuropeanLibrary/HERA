package org.theeuropeanlibrary.hera.rest.administration.service.memory;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.theeuropeanlibrary.hera.rest.administration.service.DatasetService;
import org.theeuropeanlibrary.hera.rest.administration.service.memory.dao.MemoryDatasetDao;
import org.theeuropeanlibrary.maia.common.definitions.Dataset;

/**
 *
 * @author Markus Muhr (markus.muhr@theeuropeanlibrary.org)
 * @since 10.11.2014
 */
public class MemoryDatasetService implements DatasetService {

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
    public void deleteDataSet(String dataSetId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Dataset> getDataSets(String startFrom, int numberOfDatasets) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Dataset createDataSet(String dataSetId, String description) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateDataSet(String dataSetId, String description) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
