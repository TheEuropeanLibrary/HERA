package org.theeuropeanlibrary.hera.rest.administration;

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
import org.theeuropeanlibrary.hera.rest.administration.service.DatasetService;
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
    private DatasetService<String> datasetService;

    @Value("${numberOfElementsOnPage}")
    private int numberOfDatasets;

    /**
     * Returns all data sets. Result is returned in slices.
     *
     * @param startFrom reference to next slice of result. If not provided,
     * first slice of result will be returned.
     * @return slice of data sets for given dataset.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ResultSlice<Dataset> getDataSets(@QueryParam(Q_START_FROM) String startFrom) {
    	
        ResultSlice<Dataset> dList = new ResultSlice<Dataset>();
//        dList.setResults(datasetService.getDataSets(startFrom, numberOfDatasets));
        return dList;
    }

    /**
     * Creates new data set.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @PreAuthorize("isAuthenticated()")
    public Response createDataSet(Dataset<String> dataset) {

        ParamUtil.require("dataset", dataset);

        Dataset<String> createdDataset = datasetService.createDataSet(dataset);
        return Response.ok().entity(createdDataset).build();
    }
}
