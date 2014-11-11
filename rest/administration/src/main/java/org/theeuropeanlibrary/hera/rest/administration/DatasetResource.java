package org.theeuropeanlibrary.hera.rest.administration;

import static org.theeuropeanlibrary.hera.rest.administration.ParamConstants.P_DATASET;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.theeuropeanlibrary.hera.rest.administration.service.DatasetService;
import org.theeuropeanlibrary.hera.rest.administration.service.exception.DatasetDoesNotExistException;
import org.theeuropeanlibrary.maia.common.definitions.Dataset;

/**
 * Resource to manage data sets. 
 * 
 * Get / Delete / Update dataset
 * 
 */
@Path("/datasets/{" + P_DATASET + "}")
@Component
@Scope("request")
public class DatasetResource {

	@Autowired
    private DatasetService<String> dataSetService;
    
    @Value("${numberOfElementsOnPage}")
    private int numberOfElementsOnPage;
    
    /**
     * @return Retrieves a dataset.
     * @throws DatasetDoesNotExistException 
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getDataset(@PathParam(P_DATASET) String datasetId) throws DatasetDoesNotExistException {

		Dataset<String> d = dataSetService.getDataset(datasetId);
        final Response response = Response.ok().entity(d).build();
        return response;
    }
    
    /**
     * Deletes data set.
     * @throws DatasetDoesNotExistException in case the specified dataset does not exist
     */
    @DELETE
    public Response deleteDataSet(@PathParam(P_DATASET) String dataSetId) throws DatasetDoesNotExistException {
    	
        dataSetService.deleteDataSet(dataSetId);
        return Response.ok().build();
    }

    /**
     * Updates description of data set.
     *
     * @throws DataSetNotExistsException in case the specified dataset does not exist
     * @statuscode 200 object has been updated.
     */
    @PUT
    public Response updateDataSet(@PathParam(P_DATASET) String datasetId,
    		Dataset<String> dataset) throws DatasetDoesNotExistException  {
    	
        dataSetService.updateDataSet(datasetId, dataset);
        return Response.ok().build();
    }
}
