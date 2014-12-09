package org.theeuropeanlibrary.hera.rest.administration;

import static org.theeuropeanlibrary.hera.rest.administration.ParamConstants.Q_PROVIDER;
import static org.theeuropeanlibrary.hera.rest.administration.ParamConstants.Q_START_FROM;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.theeuropeanlibrary.hera.rest.administration.service.memory.MemoryDatasetService;
import org.theeuropeanlibrary.maia.common.definitions.Dataset;

/**
 * Get / Create dataset
 * 
 */
@Path("/datasets")
@Component
@Scope("request")
public class DatasetsResource {

    @Autowired
    private MemoryDatasetService datasetService;

    @Value("${numberOfElementsOnPage}")
    private int numberOfDatasets;
    
	
    /**
     * @return
     * Returns data sets. Result is returned in slices.
     * 
     * If 
     * @param providerId is provided, only datasets of specific provider will be returned
     * (otherwise all datasets are returned).
     *
     * @param startFrom reference to next slice of result. If not provided,
     * first slice of result will be returned.
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public ResultSet<Dataset<String>> getDataSets(
    		@QueryParam(Q_PROVIDER) String providerId,
    		@QueryParam(Q_START_FROM) String startDatasetId) {

    	ResultSet<Dataset<String>> dList = new ResultSet<Dataset<String>>();
        
    	if (providerId != null) { // search only for datasets of specific provider
            dList.setResults(datasetService.getDataSetsForProvider(providerId, startDatasetId, numberOfDatasets));
    	}
    	else { // get all Datasets
            dList.setResults(datasetService.getDataSets(startDatasetId, numberOfDatasets));
    	}
        return dList;
    }

    /**
     * Creates new data set.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @PreAuthorize("isAuthenticated()")
    public Response createDataSet(@QueryParam(Q_PROVIDER) String providerId, Dataset<String> dataset) {

        ParamUtil.require("dataset", dataset);
        ParamUtil.require("providerId", providerId);

        Dataset<String> createdDataset = datasetService.createDataSet(providerId, dataset);
        return Response.ok().entity(createdDataset).build();
    }
}
